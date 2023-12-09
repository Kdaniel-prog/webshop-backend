package kiszel.daniel.webshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "CART", schema = "CIB")
public class Cart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "USER_ID")
    private int userId;
    @Basic
    @Column(name = "TOTAL")
    private Long total;
    @Basic
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Basic
    @Column(name = "ORDERED")
    private Boolean ordered;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id && userId == cart.userId && Objects.equals(total, cart.total) && Objects.equals(createdAt, cart.createdAt) && Objects.equals(ordered, cart.ordered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, total, createdAt, ordered);
    }
}
