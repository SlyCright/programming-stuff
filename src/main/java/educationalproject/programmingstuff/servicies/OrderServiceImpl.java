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

    private final OrderResponseMapper orderResponseMapper;

    @Override
    @Transactional
    public OrderResponseDto book(OrderCreateRequestDto newOrder) {
        List<RequestedItemDto> requestedItems = newOrder.getRequestedItemDtos();
        Map<Long, Integer> requestedItemsIdMappedToQuantity = makeMapOf(requestedItems);
        List<CommodityItem> availableItems = getAvailableCommodityItems(requestedItems);
        writeOff(requestedItemsIdMappedToQuantity, availableItems);
        return persistNewOrderOf(requestedItemsIdMappedToQuantity, availableItems);
    }

    private Map<Long, Integer> makeMapOf(List<RequestedItemDto> commodityItems) {
        return commodityItems.stream()
                .collect(Collectors.toMap(
                        RequestedItemDto::getItemId,
                        RequestedItemDto::getQuantity));
    }

    private List<CommodityItem> getAvailableCommodityItems(List<RequestedItemDto> requestedItemDtos) {
        Set<Long> itemsId = requestedItemDtos.stream()
                .map(RequestedItemDto::getItemId)
                .collect(Collectors.toSet());
        return commodityItemRepository.getCommodityItemsByItemIdIn(itemsId);
    }

    private void writeOff(Map<Long, Integer> requestedItemsIdMappedToQuantity, List<CommodityItem> available) {
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

    private OrderResponseDto persistNewOrderOf(
            final Map<Long, Integer> requestedItemsIdMappedToQuantity, final List<CommodityItem> available) {
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
        orderItems.forEach(orderItem->orderItem.setOrder(order));
        orderRepository.save(order);
        return orderResponseMapper.makeOrderResponseOf(order);
    }

}
