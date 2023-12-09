package kiszel.daniel.webshop.repository;

import kiszel.daniel.webshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository <Cart, Integer> {
    Optional<Cart> findCartByUserIdAndOrderedIsFalse(int user_id);
}
