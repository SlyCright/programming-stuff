package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.repositories.ItemRepository;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import educationalproject.programmingstuff.servicies.mappers.StoredItemsResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final StoredItemsResponseMapper storedItemsResponseMapper;

    private final ItemRepository itemRepository;

    private final CommodityItemRepository commodityItemRepository;

    public List<StoredItemsResponseDto> getStoredItems() {
        List<Item> items = itemRepository.findAll();
        List<CommodityItem> commodityItems = commodityItemRepository.findAll(); // don't mind it already has fielded items in it
        List<StoredItemsResponseDto> storedItemsResponseDtos = storedItemsResponseMapper.makeStoredItemDtoOf(items);

        Map<Long, Integer> itemNumberMappedByQuantity = new HashMap<>();

        commodityItems.forEach(ci -> {
            itemNumberMappedByQuantity.put(
                    ci.getItem().getId(),
                    ci.getQuantity()); // Have no idea how to use "Collectors.toMap()" Honestly, I tried
        });

        storedItemsResponseDtos.forEach(item -> {
                    item.setQuantity(
                            itemNumberMappedByQuantity.getOrDefault(
                                    item.getItemNumber(), 0)); // Is that formatting ok? (I would prefer use two intermediary variables (and same above))
                }
        );

        return storedItemsResponseDtos;
    }

}
