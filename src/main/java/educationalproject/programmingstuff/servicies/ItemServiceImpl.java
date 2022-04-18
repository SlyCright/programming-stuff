package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import educationalproject.programmingstuff.servicies.mappers.StoredItemsResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final CommodityItemRepository commodityItemRepository;

    private final StoredItemsResponseMapper storedItemsResponseMapper;

    public List<StoredItemsResponseDto> getStoredItems() {

        List<CommodityItem> commodityItems = commodityItemRepository.findAll();

        return storedItemsResponseMapper.makeStoredItemDtoOf(commodityItems);

    }

}
