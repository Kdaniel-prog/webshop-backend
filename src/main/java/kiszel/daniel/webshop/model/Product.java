package kiszel.daniel.webshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "PRODUCT", schema = "CIB")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "PRODUCT_NAME", length = 100)
    private String productName;
    @Basic
    @Column(name = "CATEGORY", length = 20)
    private String category;
    @Basic
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic
    @Column(name = "PRICE")
    private Integer price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && quantity == product.quantity && Objects.equals(productName, product.productName) && Objects.equals(category, product.category) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, category, quantity, price);
    }
}
