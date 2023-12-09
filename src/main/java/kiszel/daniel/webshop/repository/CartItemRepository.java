package kiszel.daniel.webshop.repository;

import kiszel.daniel.webshop.model.Cart;
import kiszel.daniel.webshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository <CartItem, Integer> {
    Optional<CartItem> findByProductNameAndCategoryAndPriceAndCartId(String productName, String category, int price, Cart cartId);
    List<CartItem> findAllByCartId(Cart cartId);
}
