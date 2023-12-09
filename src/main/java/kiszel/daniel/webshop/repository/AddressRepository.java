package kiszel.daniel.webshop.repository;

import kiszel.daniel.webshop.model.Address;
import kiszel.daniel.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Integer> {

}
