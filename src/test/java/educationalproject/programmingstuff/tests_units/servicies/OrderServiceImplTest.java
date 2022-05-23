package educationalproject.programmingstuff.tests_units.servicies;

import educationalproject.programmingstuff.exeptions.NotEnoughItemsAtStoreException;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.repositories.OrderRepository;
import educationalproject.programmingstuff.servicies.OrderServiceImpl;
import educationalproject.programmingstuff.servicies.dto.OrderCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.OrderResponseDto;
import educationalproject.programmingstuff.servicies.dto.RequestedItemDto;
import educationalproject.programmingstuff.servicies.mappers.OrderResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {

    @Mock
    private CommodityItemRepository commodityItemRepository;

    @Mock
    private OrderRepository orderRepository;

    @Spy
    private OrderResponseMapper orderResponseMapper;

    @Spy
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void givenOrderAndEnoughItemsAtStorehouse_whenBook_thenSuccess() {
        //Given
        List<RequestedItemDto> requestedItemDtos = List.of(
                RequestedItemDto.builder().itemId(1L).quantity(10).build(),
                RequestedItemDto.builder().itemId(3L).quantity(30).build(),
                RequestedItemDto.builder().itemId(5L).quantity(50).build());
        OrderCreateRequestDto givenOrderDto = OrderCreateRequestDto.builder()
                .requestedItemDtos(requestedItemDtos)
                .build();
        List<CommodityItem> responseCommodityItems = List.of(
                CommodityItem.builder().id(2L).item(Item.builder().id(1L).build()).quantity(20).build(),
                CommodityItem.builder().id(4L).item(Item.builder().id(3L).build()).quantity(40).build(),
                CommodityItem.builder().id(6L).item(Item.builder().id(5L).build()).quantity(60).build()
        );
        Mockito
                .when(commodityItemRepository.getCommodityItemsByItemIdIn(any()))
                .thenReturn(responseCommodityItems);
        //When
        OrderResponseDto orderResponseDto = orderService.book(givenOrderDto);
        //Then
        for (int i = 0; i < 3; i++) {
            assertThat(responseCommodityItems.get(i).getQuantity()).isEqualTo(10);
        }
    }

    @Test
    void givenOrderAndNotEnoughItemsAtStorehouse_whenBook_thenThrowsException() {
        //Given
        List<RequestedItemDto> requestedItemDtos = List.of(
                RequestedItemDto.builder().itemId(1L).quantity(10).build(),
                RequestedItemDto.builder().itemId(3L).quantity(30).build(),
                RequestedItemDto.builder().itemId(5L).quantity(50).build());
        OrderCreateRequestDto givenOrderDto = OrderCreateRequestDto.builder()
                .requestedItemDtos(requestedItemDtos)
                .build();
        List<CommodityItem> responseCommodityItems = List.of(
                CommodityItem.builder().id(2L).item(Item.builder().id(1L).build()).quantity(2).build(),
                CommodityItem.builder().id(4L).item(Item.builder().id(3L).build()).quantity(4).build(),
                CommodityItem.builder().id(6L).item(Item.builder().id(5L).build()).quantity(6).build()
        );
        Mockito
                .when(commodityItemRepository.getCommodityItemsByItemIdIn(any()))
                .thenReturn(responseCommodityItems);
        //When
        //Then
        assertThrows(NotEnoughItemsAtStoreException.class, () -> orderService.book(givenOrderDto));
    }
}