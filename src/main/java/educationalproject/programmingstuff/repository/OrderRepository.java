package educationalproject.programmingstuff.repository;

import educationalproject.programmingstuff.model.Order1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrderRepository extends JpaRepository<Order1,Long> { //todo why not crud?

}
