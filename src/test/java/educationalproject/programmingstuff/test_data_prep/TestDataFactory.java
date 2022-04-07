package educationalproject.programmingstuff.test_data_prep;

import educationalproject.programmingstuff.model.User;

import java.util.List;

public class TestDataFactory {

    static public ListBuilder<User> getUsersListBuilderWithDefaultUsers() {

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

    private TestDataFactory() {
    }

}
