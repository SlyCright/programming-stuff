package educationalproject.programmingstuff.controllers;

import educationalproject.programmingstuff.servicies.OrderService;
import educationalproject.programmingstuff.servicies.dto.OrderCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> makeOrder(@RequestBody @Valid OrderCreateRequestDto newOrder) {

        // todo: test to this controller
        // todo: if missing item then error 4xxx with message
        // todo: make custom runtime exception class "not enough item"
        // todo: this stuff with @ControllerAdvice
        // todo: Exceptions packages needed

        return ResponseEntity.ok(orderService.book(newOrder));
    }

}
