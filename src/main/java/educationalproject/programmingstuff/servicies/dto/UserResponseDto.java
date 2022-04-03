package educationalproject.programmingstuff.servicies.dto;

import educationalproject.programmingstuff.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@Value //Lombock's annotation incompatible with Jackson. Got hassle in tests.
@Getter
@Setter
public class UserResponseDto {

    Long id;

    String userName;

    String surname;

    List<Order> orders;
/*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }*/

}
