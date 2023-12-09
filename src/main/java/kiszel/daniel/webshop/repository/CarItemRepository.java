package kiszel.daniel.webshop.repository;

import kiszel.daniel.webshop.model.CartItem;
import kiszel.daniel.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarItemRepository extends JpaRepository <CartItem, Integer> {

}
