package educationalproject.programmingstuff.repository;

import educationalproject.programmingstuff.service.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ItemRepository extends JpaRepository<Item, Long> {

}
