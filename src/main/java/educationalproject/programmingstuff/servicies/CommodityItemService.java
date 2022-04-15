package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.servicies.dto.CommodityItemResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public interface CommodityItemService {

    List<CommodityItemResponseDto> getAllCommodityItems();

}
