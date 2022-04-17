package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ItemServiceImpl implements ItemService {

    public List<StoredItemsResponseDto> getStoredItems() {
        return null;
    }

}
