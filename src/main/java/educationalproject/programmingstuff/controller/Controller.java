package educationalproject.programmingstuff.controller;

import educationalproject.programmingstuff.repository.ItemRepository;
import educationalproject.programmingstuff.repository.OrderRepository;
import educationalproject.programmingstuff.repository.UserRepository;
import educationalproject.programmingstuff.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {

    private UserRepository userRepository;

    private ItemRepository itemRepository;

    private OrderRepository orderRepository;

    private MainService mainService;

    @GetMapping("/dataPrep")
    public void dataPrep() {
        mainService.prepareTestData();
    }

    @GetMapping("/dataGet")
    public List<List>  test() {

        List<List> lists=new ArrayList<>();

        lists.add(userRepository.findAll());
        lists.add(itemRepository.findAll());
        lists.add(orderRepository.findAll());

        return lists;
    }

}
