package kiszel.daniel.webshop.repository;

import kiszel.daniel.webshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <Cart, Integer> {

}
