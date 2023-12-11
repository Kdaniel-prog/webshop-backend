package kiszel.daniel.webshop.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressDTO {
    private int id;
    private String City;
    private String Door;
    private String Flat;
    private int HouseNumber;
    private String Stairs;
    private String Street;
    private String Zipcode;
}
