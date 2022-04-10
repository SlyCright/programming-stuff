package educationalproject.programmingstuff.servicies;

import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.model.Order;
import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.repositories.ItemRepository;
import educationalproject.programmingstuff.repositories.OrderRepository;
import educationalproject.programmingstuff.repositories.UserRepository;
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
                .surname("Kuznets")
                .orders(testOrders)
                .build();

        itemRepository.saveAndFlush(item);
        orderRepository.saveAndFlush(order);
        userRepository.saveAndFlush(userUno);
        userRepository.saveAndFlush(userDos);
        userRepository.saveAndFlush(userTres);
    }

}
