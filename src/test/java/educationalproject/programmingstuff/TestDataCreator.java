package educationalproject.programmingstuff;

//import educationalproject.programmingstuff.model.Item;
//import educationalproject.programmingstuff.model.Order;

import educationalproject.programmingstuff.model.Item;
import educationalproject.programmingstuff.model.Order;
import educationalproject.programmingstuff.model.User;
import educationalproject.programmingstuff.servicies.dto.UserCreateRequestDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestDataCreator {

    static public List<User> getTestUsers() {

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
                .build();

        User userTres = User.builder()
                .name("Ivan")
                .surname("Kuznets")
                .build();

        return List.of(userUno, userDos, userTres);
    }

    static public UserCreateRequestDto getUserCreateRequestDto() {
        return UserCreateRequestDto.builder()
                .userName("Sasha")
                .surname("Grey")
                .build();
    }

}
