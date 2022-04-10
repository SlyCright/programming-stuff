package educationalproject.programmingstuff.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "StoreHouse")
@Builder
public class CommodityItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    @Column(unique = true)
    private Item item;

    @NotBlank(message = "Empty title is not allowed")
    //@Column(unique = true) todo fix with fake data generation
    private String title;

    @NotBlank(message = "Empty description is not allowed")
    private String description;

    @Min(value = 0, message = "Value mus be positive or zero")
    private int quantity;

}
