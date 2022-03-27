package educationalproject.programmingstuff.service;

import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.model.Order;
import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repository.ItemRepository;
import educationalproject.programmingstuff.repository.OrderRepository;
import educationalproject.programmingstuff.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    public void prepareTestData() {

        Item item = Item.builder()
                .title("stuff")
                .description("Stuff of things")
                .price(BigDecimal.valueOf(99.99))
                .build();

        ArrayList<Item> testItems = new ArrayList<>();
        testItems.add(item);

        Order order = Order.builder()
                .items(testItems)
                .build();

        item.setOrder(order);

        ArrayList<Order> testOrders = new ArrayList<>();
        testOrders.add(order);

        User userUno = User.builder()
                .name("John")
                .surname("Smith")
                .orders(testOrders)
                .build();

        order.setUser(userUno);

        User userDos = User.builder()
                .name("John")
                .surname("NotSmith")
                .orders(testOrders)
                .build();

        User userTres = User.builder()
                .name("Ivan")
                .surname("Kuznetsov")
                .orders(testOrders)
                .build();

        itemRepository.saveAndFlush(item);
        orderRepository.saveAndFlush(order);
        userRepository.saveAndFlush(userUno);
        userRepository.saveAndFlush(userDos);
        userRepository.saveAndFlush(userTres);

    }

}
