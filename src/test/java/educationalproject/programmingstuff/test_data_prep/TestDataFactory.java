package educationalproject.programmingstuff.test_data_prep;

import educationalproject.programmingstuff.model.User;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TestDataFactory {

    public User.UserBuilder getUserBuilder(){
        return User.builder()
                .name("John")
                .surname("Smith");
    }

     public ListBuilder<User> getUsersListBuilderWithDefaultUsers() {

        return new ListBuilder<User>(
                List.of(
                        User.builder()
                                .name("John")
                                .surname("Smith")
                                .build(),
                        User.builder()
                                .name("John")
                                .surname("NotSmith")
                                .build(),
                        User.builder()
                                .name("Ivan")
                                .surname("Kuznets")
                                .build()
                )
        );

    }

}
