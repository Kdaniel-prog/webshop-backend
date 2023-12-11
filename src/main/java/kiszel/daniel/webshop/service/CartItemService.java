package kiszel.daniel.webshop.service;

import kiszel.daniel.webshop.DTO.CartItemDTO;
import kiszel.daniel.webshop.model.Cart;
import kiszel.daniel.webshop.model.CartItem;
import kiszel.daniel.webshop.model.Product;
import kiszel.daniel.webshop.repository.CartItemRepository;
import kiszel.daniel.webshop.repository.CartRepository;
import kiszel.daniel.webshop.repository.ProductRepository;
import kiszel.daniel.webshop.util.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public Map<String, Integer> numberOfItems(int cartId){
        Optional<Cart> cart = cartRepository.findById(cartId);
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cart.get());
        Integer counted = cartItems.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        Map<String, Integer> count = new HashMap<>();
        count.put("count",counted);
        return count;

    }

    public List<CartItem> getItems(int cartId){
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cartItemRepository.findAllByCartId(cart.get());
    }

    /**
     * @param cartItemDTO includ the quantity, product id, cart id,
     *                    isIncrease mean if we want to add or remove item from the cart
     * This function is add or remove product quantity to a cart_item
     * If the product is doesnt had enough quantity than we return with an 400 error msg
     * */
    public void addCartItemOrRemove(CartItemDTO cartItemDTO){

        Optional<Product> product;
        if(cartItemDTO.isIncrease()){
            product = productRepository.findById(cartItemDTO.getProductId());
        }else{
            product = productRepository.findByProductNameAndCategoryAndPrice(
                    cartItemDTO.getProductName(),
                    cartItemDTO.getCategory(),
                    cartItemDTO.getPrice());
        }

        if(product.isEmpty()){
            throw new BadRequestException("Product not founded");
        }
        if(cartItemDTO.isIncrease()){
            product.get().setQuantity(product.get().getQuantity() - cartItemDTO.getQuantity());
        }else{
            product.get().setQuantity(product.get().getQuantity() + cartItemDTO.getQuantity());
        }

        if(product.get().getQuantity() < 0){
            throw new BadRequestException("Product dont have enough quantity");
        }

        Optional<Cart> cart = cartRepository.findById(cartItemDTO.getCartId());

        if(cart.isEmpty()){
            throw new BadRequestException("Cart not founded");
        }

        if(cart.get().getOrdered()){
            throw new BadRequestException("Cart is already Ordered");
        }

        Optional<CartItem> cartItem = cartItemRepository.findByProductNameAndCategoryAndPriceAndCartId(
                product.get().getProductName(),
                product.get().getCategory(),
                product.get().getPrice(),
                cart.get()
        );

        if(cartItem.isEmpty() && !cartItemDTO.isIncrease()){
            throw new BadRequestException("Product is not in your cart");
        }

        if(!cartItemDTO.isIncrease() && cartItem.get().getQuantity() < cartItemDTO.getQuantity()){
            throw new BadRequestException("You have less Quantity what you trying to decrease");
        }

        CartItem handledCartItem = cartItem.orElseGet(() -> CartItem.builder()
                .productName(product.get().getProductName())
                .category(product.get().getCategory())
                .quantity(cartItemDTO.getQuantity())
                .price(product.get().getPrice())
                .cartId(cart.get())
                .build());

        if(cartItemDTO.isIncrease()){
            if(cartItem.isPresent()){
                handledCartItem.setQuantity(cartItem.get().getQuantity() + cartItemDTO.getQuantity());
            }else{
                handledCartItem.setQuantity(cartItemDTO.getQuantity());

            }
            cart.get().setTotal(cart.get().getTotal() + cartItemDTO.getFullPrice());
        }else{
            handledCartItem.setQuantity(handledCartItem.getQuantity() - cartItemDTO.getQuantity());
            long total = cart.get().getTotal() - cartItemDTO.getFullPrice();
            cart.get().setTotal(total);
        }

        cartRepository.save(cart.get());
        if(handledCartItem.getQuantity() <= 0){
            handledCartItem.setCartId(null);
            cartItemRepository.save(handledCartItem);
            cartItemRepository.deleteById(cartItem.get().getId());
        }else{
            cartItemRepository.save(handledCartItem);
        }
        productRepository.save(product.get());
    }

}
