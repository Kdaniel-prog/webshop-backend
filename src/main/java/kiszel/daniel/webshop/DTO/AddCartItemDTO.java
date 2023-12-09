package kiszel.daniel.webshop.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kiszel.daniel.webshop.model.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddCartItemDTO {
    @NotBlank(message = "Product Id is Blank")
    @NotNull(message = "Product ID is NULL")
    private int productId;

    @NotBlank(message = "Quantity is Blank")
    @NotNull(message = "Quantity is NULL")
    private int quantity;

    @NotBlank(message = "Cart id is Blank")
    @NotNull(message = "Cart id is NULL")
    private int cartId;

}
