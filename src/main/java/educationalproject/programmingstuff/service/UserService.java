package educationalproject.programmingstuff.service;

import educationalproject.programmingstuff.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsersByName(String name);

    List<User> getAllUsers();
}
