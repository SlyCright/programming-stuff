package educationalproject.programmingstuff.tests_integrations;

import educationalproject.programmingstuff.controller.UserController;
import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.model.Order;
import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repositories.ItemRepository;
import educationalproject.programmingstuff.repositories.OrderRepository;
import educationalproject.programmingstuff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

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
        userRepository.saveAndFlush(userUno);
        userRepository.saveAndFlush(userDos);
        userRepository.saveAndFlush(userTres);
    }

    @Test
    void givenGetUsersEndpointIncomes_whenResponseUsersTriggered_thenCorrectUsersListReturned() throws Exception {

        //Given

        //When

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/users"));

        // Then

        resultActions.andExpect(status().isOk());

    }
}