package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public interface ItemService {

    List<StoredItemsResponseDto> getStoredItems();

}
