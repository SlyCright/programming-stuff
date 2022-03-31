package educationalproject.programmingstuff.tests_integrations;

import educationalproject.programmingstuff.servicies.UserService;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;
import educationalproject.programmingstuff.servicies.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional  // To reviewer: check this out. I've google this by myself. Test won't rise up without this.
        // Now explanation needed. I didn't google explanation yet :)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void givenUserCreateRequestDto_whenCreateNewUser_thenUserWithUniqIdAppears() {

        UserCreateRequestDto userShoudBeCreated;

        //Given

        userShoudBeCreated = UserCreateRequestDto.builder()
                .userName("John")
                .surname("Smith")
                .build();

        int i = 0;
        List<UserResponseDto> users = userService.getAllUsers();
        if (users != null) {
            i = users.size();
        }

        //When

        userService.createUser(userShoudBeCreated);
        users = userService.getAllUsers();

        //Then

        assertNotNull(users);
        assertEquals(i + 1, users.size());
        UserResponseDto userWhichWasReturned = users.get(users.size() - 1);
        assertNotNull(userWhichWasReturned.getId());
        assertEquals(userShoudBeCreated.getUserName(), userWhichWasReturned.getUserName());
        assertEquals(userShoudBeCreated.getSurname(), userWhichWasReturned.getSurname());

    }
}