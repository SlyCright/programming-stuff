package educationalproject.programmingstuff.controller;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repository.ItemRepository;
import educationalproject.programmingstuff.repository.OrderRepository;
import educationalproject.programmingstuff.repository.UserRepository;
import educationalproject.programmingstuff.service.TestService;
import educationalproject.programmingstuff.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; //todo q: What if several implementations exits? What object will be injected?

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    private final TestService testService;

    boolean isPreparationWasStartedOnce = false;

    @GetMapping("/users")
    public ResponseEntity<List<User>> usersByName(@RequestParam String name) {

        //todo q: What if "name" is empty? What if include numbers or something? where and when we checks requests?

        if (!isPreparationWasStartedOnce) {
            testService.prepareTestData();
            isPreparationWasStartedOnce = true;
        }

        List<User> users = userService.getUsersByName(name);

        int usersTotal=users.size(); //todo q: how to make two-parts response? If "usersTotal" used in HTML header for example.

        return ResponseEntity.ok(users);

    }

}
