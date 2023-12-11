package kiszel.daniel.webshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CART_ITEM", schema = "CIB")
public class CartItem {
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

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID", referencedColumnName = "id")
    private Cart cartId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && quantity == cartItem.quantity && Objects.equals(productName, cartItem.productName) && Objects.equals(category, cartItem.category) && Objects.equals(price, cartItem.price) && Objects.equals(cartId, cartItem.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, category, quantity, price, cartId);
    }
}
