package educationalproject.programmingstuff.tests_units.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import educationalproject.programmingstuff.controllers.ItemController;
import educationalproject.programmingstuff.data.DtoGenerator;
import educationalproject.programmingstuff.servicies.ItemServiceImpl;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @MockBean
    private ItemServiceImpl itemService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper jacksonMapper = new ObjectMapper();

    @Test
    void givenItemsRequest_whenResponseStoredItems_thenCorrectResponse() throws Exception {

        //Given
        List<StoredItemsResponseDto> expectedItems = List.of(
                DtoGenerator.getStoredItemsResponseDtoBuilder().build(),
                DtoGenerator.getStoredItemsResponseDtoBuilder().build(),
                DtoGenerator.getStoredItemsResponseDtoBuilder().build());
        String expectedResponse = jacksonMapper.writeValueAsString(expectedItems);


        Mockito.when(itemService.getStoredItems()).thenReturn(expectedItems);

        //When
        ResultActions result = mockMvc.perform(get("/items"));

        //Then
        result.andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

}