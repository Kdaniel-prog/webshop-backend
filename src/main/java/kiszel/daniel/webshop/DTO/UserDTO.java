package kiszel.daniel.webshop.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private int id;
    private String Firstname;
    private String Lastname;
    private String Username;
    private AddressDTO billingAddress;
    private AddressDTO deliveryAddress;
}
