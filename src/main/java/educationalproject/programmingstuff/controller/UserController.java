package educationalproject.programmingstuff.controller;

import educationalproject.programmingstuff.servicies.UserService;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> responseUsers(@RequestParam(required = false) String name) {

        List<UserResponseDto> users;

        if (name != null) {
            users = userService.getUsersByName(name);
        } else {
            users = userService.getAllUsers();
        }

        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto user) { //todo q: optional in swagger
        return ResponseEntity.ok(userService.createUser(user));
    }

}
