package educationalproject.programmingstuff.controllers;

import educationalproject.programmingstuff.servicies.UserService;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> responseUsers(@RequestParam(required = false) @Nullable String name) {
        Optional<String> nameOptional = Optional.ofNullable(name); // senior level programming :)
        return ResponseEntity.ok(
                nameOptional.isPresent() ?
                        userService.getUsersByName(nameOptional.get()) :
                        userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateRequestDto user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

}
