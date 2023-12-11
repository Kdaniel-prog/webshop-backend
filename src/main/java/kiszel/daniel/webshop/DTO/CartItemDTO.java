package kiszel.daniel.webshop.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartItemDTO {
    @NotBlank(message = "Product Id is Blank")
    @NotNull(message = "Product ID is NULL")
    private int productId;

    @NotBlank(message = "Quantity is Blank")
    @NotNull(message = "Quantity is NULL")
    private int quantity;

    @NotBlank(message = "Cart id is Blank")
    @NotNull(message = "Cart id is NULL")
    private int cartId;

    @NotBlank(message = "Increase is Blank")
    @NotNull(message = "Increase is NULL")
    private boolean isIncrease;

    private String productName;
    private String category;
    private int price;
    private int fullPrice;
}
