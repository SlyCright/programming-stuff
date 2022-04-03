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
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void givenUserCreateRequestDto_whenCreateNewUser_thenReturnUseWithUniqId() {

        //Given

        UserCreateRequestDto userShouldBeCreated = UserCreateRequestDto.builder()
                .userName("John")
                .surname("Smith")
                .build();

        int i = 0;
        List<UserResponseDto> users = userService.getAllUsers();
        if (users != null) {
            i = users.size();
        }

        //When

        userService.createUser(userShouldBeCreated);

        //Then

        users = userService.getAllUsers();
        UserResponseDto userWhichWasReturned = users.get(users.size() - 1);

        assertNotNull(users);
        assertEquals(i + 1, users.size());
        assertNotNull(userWhichWasReturned.getId());
        assertEquals(userShouldBeCreated.getUserName(), userWhichWasReturned.getUserName());
        assertEquals(userShouldBeCreated.getSurname(), userWhichWasReturned.getSurname());

    }
}