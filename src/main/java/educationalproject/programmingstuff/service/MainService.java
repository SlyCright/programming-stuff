package educationalproject.programmingstuff.service;

import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.model.Order1;
import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repository.ItemRepository;
import educationalproject.programmingstuff.repository.OrderRepository;
import educationalproject.programmingstuff.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class MainService {

    private UserRepository userRepository;

    private ItemRepository itemRepository;

    private OrderRepository orderRepository;

    public void prepareTestData() {

        User userUno = User.builder()
                .name("Ivan")
                .surname("Smith")
                .build();
        userRepository.save(userUno);

        User userDos = User.builder()
                .name("John")
                .surname("Ivanov")
                .build();
        userRepository.save(userDos);

        userRepository.flush();

        Item itemUno = Item.builder()
                .title("stuff")
                .description("Stuff of things")
                .price(BigDecimal.valueOf(99.99))
                .build();
        itemRepository.save(itemUno);

        Item itemDos = Item.builder()
                .title("things")
                .description("Things of stuff")
                .price(BigDecimal.valueOf(99.99))
                .build();
        itemRepository.save(itemDos);

        itemRepository.flush();

        Order1 orderUno = Order1.builder().build();
        orderRepository.save(orderUno);

        Order1 orderDos = Order1.builder().build();
        orderRepository.save(orderDos);

        orderRepository.flush();
    }

}
