package educationalproject.programmingstuff.unit.servicies;

import educationalproject.programmingstuff.servicies.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void givenUserCreateRequestDto_whenCreateNewUser_thenUserWithUniqIdAppears() {
        //Given
//User user=User().builder().
        //When
        //Then
    }
}