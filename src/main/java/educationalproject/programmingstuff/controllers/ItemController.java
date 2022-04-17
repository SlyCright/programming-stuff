package educationalproject.programmingstuff.controllers;

import educationalproject.programmingstuff.servicies.ItemService;
import educationalproject.programmingstuff.servicies.dto.StoredItemsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<StoredItemsResponseDto>> responseStoredItems() {

        return ResponseEntity.ok(itemService.getStoredItems());
    }

}
