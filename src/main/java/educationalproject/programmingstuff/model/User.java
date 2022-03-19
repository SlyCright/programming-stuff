package educationalproject.programmingstuff.model;

import lombok.Data;

import java.net.PortUnreachableException;
import java.util.ArrayList;

@Data
public class User {
    private long id;
    private String name;
    private String surname;
    private Order currentOrder;
    private ArrayList<Order> fulfilledOrders;
}
