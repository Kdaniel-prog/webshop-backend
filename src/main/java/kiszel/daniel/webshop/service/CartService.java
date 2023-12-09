package kiszel.daniel.webshop.service;

import kiszel.daniel.webshop.model.Cart;
import kiszel.daniel.webshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    public Cart getCart(int userId){
        Optional<Cart> cart = cartRepository.findCartByUserIdAndOrderedIsNull(userId);
        if(cart.isEmpty()){
            // Create a Timestamp object with the current date and time
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(currentTimestamp.getTime());
            return cartRepository.save(Cart.builder().userId(userId).createdAt(date).total(0L).build());
        }
        return cart.get();
    }

}
