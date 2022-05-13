package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.exeptions.NotEnoughItemsAtStoreException;
import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.model.Order;
import educationalproject.programmingstuff.model.OrderItem;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.repositories.OrderRepository;
import educationalproject.programmingstuff.servicies.dto.OrderCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.OrderResponseDto;
import educationalproject.programmingstuff.servicies.dto.RequestedItemDto;
import educationalproject.programmingstuff.servicies.mappers.OrderResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CommodityItemRepository commodityItemRepository;

    private final OrderRepository orderRepository;
    private OrderResponseMapper orderResponseMapper;

    @Override
    @Transactional
    public OrderResponseDto book(OrderCreateRequestDto newOrder) {
        List<RequestedItemDto> requested = newOrder.getRequestedItemDtos();
        List<CommodityItem> available = getAvailableCommodityItems(requested);
        return writeOff(requested, available);
    }

    private List<CommodityItem> getAvailableCommodityItems(List<RequestedItemDto> requestedItemDtos) {
        Set<Long> itemsId = requestedItemDtos.stream()
                .map(RequestedItemDto::getItemId)
                .collect(Collectors.toSet());
        return commodityItemRepository.getCommodityItemsByItemIdIn(itemsId);
    }

    private OrderResponseDto writeOff(List<RequestedItemDto> requested, List<CommodityItem> available) {
        Map<Long, Integer> requestedItemsIdMappedToQuantity = makeMapOf(requested);
        WriteOffOrThrowException(available, requestedItemsIdMappedToQuantity);

        List<OrderItem> orderItems = available.stream()
                .map(availableCommodityItem -> {
                    Item item = availableCommodityItem.getItem();
                    return OrderItem.builder()
                            .item(item)
                            .quantity(requestedItemsIdMappedToQuantity.get(item.getId()))
                            .build();
                })
                .collect(Collectors.toList());

        Order order = Order.builder().orderItems(orderItems).build();

        orderRepository.save(order);

        return orderResponseMapper.makeOrderResponseOf(order);
    }

    private Map<Long, Integer> makeMapOf(List<RequestedItemDto> commodityItems) {
        return commodityItems.stream()
                .collect(Collectors.toMap(
                        RequestedItemDto::getItemId,
                        RequestedItemDto::getQuantity));
    }

    private void WriteOffOrThrowException(
            List<CommodityItem> available, Map<Long, Integer> requestedItemsIdMappedToQuantity) {
        available.forEach(
                availableItem -> {
                    long itemId = availableItem.getItem().getId();
                    int requestedQuantity = requestedItemsIdMappedToQuantity.get(itemId);
                    int availableQuantity = availableItem.getQuantity();
                    int remainingQuantity = availableQuantity - requestedQuantity;
                    if (remainingQuantity < 0) {
                        throw new NotEnoughItemsAtStoreException(
                                "Requested " + requestedQuantity +
                                        " items with item number " + itemId +
                                        " but there is only " + availableQuantity +
                                        " items at the storehouse.");
                    }
                    availableItem.setQuantity(remainingQuantity);
                });
    }

}
