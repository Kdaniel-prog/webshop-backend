package kiszel.daniel.webshop.repository;

import kiszel.daniel.webshop.model.Product;
import kiszel.daniel.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Integer> {
    Optional<Product> findByProductNameAndCategoryAndPrice(String name, String category, int price);
}
