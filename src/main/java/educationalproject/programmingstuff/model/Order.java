package educationalproject.programmingstuff.model;

import lombok.Data;

import java.util.Map;

@Data
public class Order {
    private long id;
    private long userId;
    private Map<Long, Integer> itemsTable;
}
