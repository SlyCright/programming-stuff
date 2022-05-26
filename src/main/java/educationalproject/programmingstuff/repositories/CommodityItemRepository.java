package educationalproject.programmingstuff.repositories;

import educationalproject.programmingstuff.model.CommodityItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CommodityItemRepository extends JpaRepository<CommodityItem, Long> {

//        @Query(nativeQuery = true,
//            value = "SELECT item.id, commodity_item.id, commodity_item.item_id, item.title, item.description, item.price, commodity_item.quantity " +
//                    "FROM items AS item " +
//                    "JOIN storehouse AS commodity_item " + // "FULL JOIN" doesn't works with "syntax error". Why?
//                    "ON item.id=commodity_item.item_id;")

    @Query("SELECT ci, i FROM CommodityItem ci JOIN FETCH Item AS i ON ci.item.id=i.id")
    List<CommodityItem> getAllCommodityItemsWithFetchedItems();

    @Query("SELECT c FROM CommodityItem c JOIN FETCH Item AS i ON c.item.id=i.id WHERE c.item.id IN :itemIds")
    List<CommodityItem> getCommodityItemsByItemIdIn(Set<Long> itemIds);

}
