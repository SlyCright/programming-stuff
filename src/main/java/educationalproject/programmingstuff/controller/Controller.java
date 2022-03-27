package educationalproject.programmingstuff.controller;

import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repository.ItemRepository;
import educationalproject.programmingstuff.repository.OrderRepository;
import educationalproject.programmingstuff.repository.UserRepository;
import educationalproject.programmingstuff.service.TestService;
import educationalproject.programmingstuff.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final UserServiceImpl userService;

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    private final TestService testService;

    @GetMapping("/dataPrep")
    public void dataPrep() {
        testService.prepareTestData();
    }

    @GetMapping("/dataGet")
    public List<List> dataGet() {

        List<List> lists = new ArrayList<>();

        lists.add(userRepository.findAll());
        lists.add(itemRepository.findAll());
        lists.add(orderRepository.findAll());

        return lists;
    }

    @GetMapping("/userByName")
    public List<User> usersByName(@RequestParam String name) {

        List<User> users = userService.getUsersByName(name);

        return  users;

    }

}
