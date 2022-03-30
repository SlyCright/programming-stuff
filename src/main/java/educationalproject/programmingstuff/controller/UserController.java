package educationalproject.programmingstuff.controller;

import educationalproject.programmingstuff.servicies.UserService;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final educationalproject.programmingstuff.servicies.TestService testService;

    boolean isPreparationWasStartedOnce = false;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> responseUsers(@Valid @RequestParam(required = false) String name) {
//todo q: @Valid annotation explanation needed. Why a man should set it?

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

    @PutMapping
    public ResponseEntity<UserResponseDto> postUser(@RequestBody UserResponseDto user) { //todo q: optional in swagger
        return ResponseEntity.ok(userService.saveUser(user));
    }

}
