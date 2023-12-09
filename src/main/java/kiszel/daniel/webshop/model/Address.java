package kiszel.daniel.webshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "ADDRESS", schema = "CIB", catalog = "")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "ZIP_CODE", length = 4)
    private String zipCode;
    @Basic
    @Column(name = "CITY", length = 50)
    private String city;
    @Basic
    @Column(name = "STREET", length = 100)
    private String street;
    @Basic
    @Column(name = "HOUSE_NUMBER")
    private int houseNumber;
    @Basic
    @Column(name = "STAIRS", length = 50)
    private String stairs;
    @Basic
    @Column(name = "FLAT", length = 50)
    private String flat;
    @Basic
    @Column(name = "DOOR", length = 50)
    private String door;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && houseNumber == address.houseNumber && Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(stairs, address.stairs) && Objects.equals(flat, address.flat) && Objects.equals(door, address.door);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zipCode, city, street, houseNumber, stairs, flat, door);
    }
}
