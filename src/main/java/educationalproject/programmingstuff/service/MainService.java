package educationalproject.programmingstuff.service;

import educationalproject.programmingstuff.repository.ItemRepository;
import educationalproject.programmingstuff.repository.OrderRepository;
import educationalproject.programmingstuff.repository.UserRepository;
import educationalproject.programmingstuff.service.model.Item;
import educationalproject.programmingstuff.service.model.Order;
import educationalproject.programmingstuff.service.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service

public class MainService {

    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

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

        Order orderUno = Order.builder().build();
        orderRepository.save(orderUno);

        Order orderDos = Order.builder().build();
        orderRepository.save(orderDos);

        orderRepository.flush();
    }

}
