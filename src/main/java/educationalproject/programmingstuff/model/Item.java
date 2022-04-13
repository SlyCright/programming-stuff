package educationalproject.programmingstuff.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String title;

    @NotBlank
    private String description;

    @NotNull
    //todo q: how to make "@ValueOfPrice(equal or higher than 0.01)" constrain
    private BigDecimal price;

    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> orderItems;

}

