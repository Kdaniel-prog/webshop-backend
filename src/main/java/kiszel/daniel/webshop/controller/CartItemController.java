package kiszel.daniel.webshop.controller;

import jakarta.validation.Valid;
import kiszel.daniel.webshop.DTO.CartItemDTO;
import kiszel.daniel.webshop.model.CartItem;
import kiszel.daniel.webshop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart_item")
@Validated
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
    @PostMapping
    public ResponseEntity addCartItemOrRemove(@Valid @RequestBody CartItemDTO cartItemDTO){
        cartItemService.addCartItemOrRemove(cartItemDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItem>> getItems(@PathVariable("cartId") int cartId){
        return new ResponseEntity<>(cartItemService.getItems(cartId),HttpStatus.OK);
    }
}
