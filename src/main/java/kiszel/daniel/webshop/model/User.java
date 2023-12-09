package kiszel.daniel.webshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "USER", schema = "CIB")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "LAST_NAME", length = 100)
    private String lastName;

    @Basic
    @Column(name = "FIRST_NAME", length = 100)
    private String firstName;

    @Basic
    @Column(name = "USERNAME", length = 50)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BILLING_ADDRESS", referencedColumnName = "id")
    private Address billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ADDRESS", referencedColumnName = "id")
    private Address deliveryAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(username, user.username) && Objects.equals(billingAddress, user.billingAddress) && Objects.equals(deliveryAddress, user.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, username, billingAddress, deliveryAddress);
    }
}
