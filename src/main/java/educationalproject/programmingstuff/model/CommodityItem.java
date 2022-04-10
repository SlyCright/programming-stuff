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
    long id;

    @NotBlank(message = "Empty title is not allowed")
    String title;

    @NotBlank(message = "Empty description is not allowed")
    String description;

    @Min(value = 0, message = "Value mus be positive or zero")
    int quantity;

}
