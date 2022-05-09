package educationalproject.programmingstuff.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Storehouse")
@Builder
public class CommodityItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @Min(value = 0, message = "Value must be positive or zero")
    private int quantity;

}
