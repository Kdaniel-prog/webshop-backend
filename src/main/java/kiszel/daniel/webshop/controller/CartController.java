package kiszel.daniel.webshop.controller;

import kiszel.daniel.webshop.model.Cart;
import kiszel.daniel.webshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{user_id}")
    public ResponseEntity<Cart> getProducts(@PathVariable("user_id") int userId){
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }
    @GetMapping("/ordered/{user_id}")
    public ResponseEntity finishCart(@PathVariable("user_id") int userId){
        cartService.finishCart(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
