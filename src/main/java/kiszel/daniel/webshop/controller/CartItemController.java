package kiszel.daniel.webshop.controller;

import jakarta.validation.Valid;
import kiszel.daniel.webshop.DTO.AddCartItemDTO;
import kiszel.daniel.webshop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart_item")
@Validated
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
    @PostMapping
    public ResponseEntity addCartItem(@Valid @RequestBody AddCartItemDTO addCartItemDTO){
        // Add Cart Item to the Cart if Cart not exist create one for the user
        cartItemService.addCartItem(addCartItemDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
