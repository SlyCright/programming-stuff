package educationalproject.programmingstuff.service;

import lombok.Data;

import java.util.Map;

@Data

public class Storehouse {
    private Map<Long, Integer> itemsTable;
}
