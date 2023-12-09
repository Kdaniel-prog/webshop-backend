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

import java.util.List;
import java.util.Optional;


@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<CartItem> getItems(int cartId){
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cartItemRepository.findAllByCartId(cart.get());
    }

    public void addCartItemOrRemove(CartItemDTO cartItemDTO){
        Optional<Product> product = productRepository.findById(cartItemDTO.getProductId());

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
            handledCartItem.setQuantity(handledCartItem.getQuantity() + cartItemDTO.getQuantity());
            cart.get().setTotal(cart.get().getTotal() + (long) handledCartItem.getPrice() * handledCartItem.getQuantity());
        }else{
            handledCartItem.setQuantity(handledCartItem.getQuantity() - cartItemDTO.getQuantity());
            if(handledCartItem.getQuantity() <= 0){
                cart.get().setTotal(cart.get().getTotal() - (long) handledCartItem.getPrice() * cartItemDTO.getQuantity());
            }else{
                cart.get().setTotal(cart.get().getTotal() - (long) handledCartItem.getPrice() * handledCartItem.getQuantity());

            }
        }

        cartRepository.save(cart.get());
        if(handledCartItem.getQuantity() <= 0){
            cartItemRepository.delete(handledCartItem);
        }else{
            cartItemRepository.save(handledCartItem);
        }
        productRepository.save(product.get());
    }

}
