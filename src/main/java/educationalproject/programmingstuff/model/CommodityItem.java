package educationalproject.programmingstuff.model;

import lombok.AllArgsConstructor;
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
public class CommodityItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @NotBlank(message = "Empty title is not allowed")
    String title;

    @NotBlank(message = "Empty description is not allowed")
    String Description;

    @Min(value=0,message="Value mus be positive or zero")
    int quantity;

}
