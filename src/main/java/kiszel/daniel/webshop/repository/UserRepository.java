package kiszel.daniel.webshop.repository;

import kiszel.daniel.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {
    Optional<User> findById(int userId);

    @Query("SELECT u.id FROM User u WHERE u.username =:name")
    int getUserIdByUsername(@Param("name")String username);

}
