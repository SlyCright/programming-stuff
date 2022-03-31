package educationalproject.programmingstuff.repositories;

import educationalproject.programmingstuff.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> getUsersByName(String name);

    List<User> getUsersByIdIsNotNull();

}
