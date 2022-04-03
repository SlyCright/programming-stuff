package educationalproject.programmingstuff.tests_integrations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.model.Order;
import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repositories.ItemRepository;
import educationalproject.programmingstuff.repositories.OrderRepository;
import educationalproject.programmingstuff.repositories.UserRepository;
import educationalproject.programmingstuff.servicies.UserService;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import educationalproject.programmingstuff.servicies.mappers.UserResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EndpointsProcessingTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Autowired
    private UserService userService;

    private RequestBuilder request;

    private List<User> userEntities;

    private List<UserResponseDto> userResponseDtos;

    private String expectedResponse;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void prepareTestData() {

        Item item = Item.builder()
                .title("stuff")
                .description("Stuff of things")
                .price(BigDecimal.valueOf(99.99))
                .build();

        ArrayList<Item> testItems = new ArrayList<>();
        testItems.add(item);

        Order order = Order.builder()
                .items(testItems)
                .build();

        item.setOrder(order);

        ArrayList<Order> testOrders = new ArrayList<>();
        testOrders.add(order);

        User userUno = User.builder()
                .name("John")
                .surname("Smith")
                .orders(testOrders)
                .build();

        order.setUser(userUno);

        User userDos = User.builder()
                .name("John")
                .surname("NotSmith")
                .build();

        User userTres = User.builder()
                .name("Ivan")
                .surname("Kuznets")
                .build();

        itemRepository.saveAndFlush(item);
        orderRepository.saveAndFlush(order);
        userRepository.saveAllAndFlush(List.of(userUno, userDos, userTres));

    }

    @Test
    @Transactional
    void givenGetEndpointUsersWithNoParam_whenEndpointTriggered_thenReturnAllUsers() throws Exception {

        //Given
        request = MockMvcRequestBuilders.get("/users");
        userEntities = userRepository.getUsersByIdIsNotNull();
        userResponseDtos = userResponseMapper.makeUsersResponseOf(userEntities);
        expectedResponse = objectMapper.writeValueAsString(userResponseDtos);

        //When
        ResultActions resultActions = mockMvc.perform(request);

        //Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    @Transactional
    void givenGetEndpointUsersWithName_whenEndpointTriggered_thenReturnUsersWithThisName() throws Exception {

        //Given
        String testName = "John";
        request = MockMvcRequestBuilders.get("/users").param("name", testName);
        userEntities = userRepository.getUsersByName(testName);
        userResponseDtos = userResponseMapper.makeUsersResponseOf(userEntities);
        expectedResponse = objectMapper.writeValueAsString(userResponseDtos);

        //When
        ResultActions resultActions = mockMvc.perform(request);

        //Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    @Transactional
    void givenPostEndpointUsersWithNewUser_whenEndpointTriggered_thenReturnThatUserWithNewId() throws Exception {

        //Given

        String testName = "Sasha";
        String testSurname = "Grey";

        RequestBuilder request = prepareRequest(testName, testSurname);

        int usersTotalBeforeNewOneCreation = getAmountOfRegisteredUsers();

        //When
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        //Then
        MockHttpServletResponse response = mvcResult.getResponse();
        String content = response.getContentAsString();
        UserResponseDto userResponseDto = objectMapper.readValue(content, UserResponseDto.class);

        assertNotNull(userResponseDto.getId());
        assertEquals(usersTotalBeforeNewOneCreation + 1, getAmountOfRegisteredUsers());
        assertEquals(testName,userResponseDto.getUserName());
        assertEquals(testSurname,userResponseDto.getSurname());
        assertNull(userResponseDto.getOrders());

    }

    private RequestBuilder prepareRequest(String testName, String testSurname) throws JsonProcessingException {

        UserCreateRequestDto userCreateRequestDto = UserCreateRequestDto.builder()
                .userName(testName)
                .surname(testSurname)
                .build();

        String body = objectMapper.writeValueAsString(userCreateRequestDto);

        request = MockMvcRequestBuilders.post("/users")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);

        return request;
    }

    private int getAmountOfRegisteredUsers() {

        List<UserResponseDto> users = userService.getAllUsers();

        if (users != null) {
            return users.size();
        }

        return 0;
    }

}