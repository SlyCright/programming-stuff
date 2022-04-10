package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.model.CommodityItem;
import educationalproject.programmingstuff.repositories.CommodityItemRepository;
import educationalproject.programmingstuff.servicies.dto.CommodityItemResponseDto;
import educationalproject.programmingstuff.servicies.mappers.CommodityItemResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class CommodityItemServiceImpl implements CommodityItemService {

    @Autowired
    private CommodityItemRepository commodityItemRepository;

    @Autowired
    private CommodityItemResponseMapper commodityItemResponseMapper;

    @Override
    public List<CommodityItemResponseDto> getAllCommodityItems() {
        List<CommodityItem> commodityItems = commodityItemRepository.findAll();
        return commodityItemResponseMapper.makeCommodityItemResponseOf(commodityItems);
    }

}
