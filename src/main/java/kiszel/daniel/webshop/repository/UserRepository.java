package kiszel.daniel.webshop.repository;

import kiszel.daniel.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {

}
