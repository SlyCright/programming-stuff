package educationalproject.programmingstuff.controller;


import educationalproject.programmingstuff.repository.ItemRepository;
import educationalproject.programmingstuff.repository.OrderRepository;
import educationalproject.programmingstuff.repository.UserRepository;
import educationalproject.programmingstuff.service.TestService;
import educationalproject.programmingstuff.service.UserService;
import educationalproject.programmingstuff.service.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    private final TestService testService;

    boolean isPreparationWasStartedOnce = false;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> usersByName(@RequestParam(required = false) String name) {

        if (!isPreparationWasStartedOnce) {
            testService.prepareTestData();
            isPreparationWasStartedOnce = true;
        }

        List<UserResponseDto> users;

        if (name != null) {
            users = userService.getUsersByName(name);
        } else {
            users = userService.getAllUsers();
        }

        return ResponseEntity.ok(users);

    }

}
