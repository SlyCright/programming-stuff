package educationalproject.programmingstuff.repositories;

import educationalproject.programmingstuff.model.CommodityItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityItemRepository extends JpaRepository<CommodityItem,Long> {
}
