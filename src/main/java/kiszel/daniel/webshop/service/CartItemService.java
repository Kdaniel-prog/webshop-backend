package kiszel.daniel.webshop.service;

import kiszel.daniel.webshop.DTO.AddCartItemDTO;
import kiszel.daniel.webshop.model.Cart;
import kiszel.daniel.webshop.model.CartItem;
import kiszel.daniel.webshop.model.Product;
import kiszel.daniel.webshop.repository.CartItemRepository;
import kiszel.daniel.webshop.repository.CartRepository;
import kiszel.daniel.webshop.repository.ProductRepository;
import kiszel.daniel.webshop.util.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public void addCartItem(AddCartItemDTO addProductDTO){
        Optional<Product> product = productRepository.findById(addProductDTO.getProductId());

        if(product.isEmpty()){
            throw new BadRequestException("Product not founded");
        }
        product.get().setQuantity(product.get().getQuantity() - addProductDTO.getQuantity());

        if(product.get().getQuantity() < 0){
            throw new BadRequestException("Product quantity is less than the wanted quantity");
        }

        Optional<Cart> cart = cartRepository.findById(addProductDTO.getCartId());

        if(cart.isEmpty()){
            throw new BadRequestException("Cart not founded");
        }

        Optional<CartItem> cartItem = cartItemRepository.findByProductNameAndCategoryAndPrice(
                product.get().getProductName(),
                product.get().getCategory(),
                product.get().getPrice());

        CartItem handledCartItem = cartItem.orElseGet(() -> CartItem.builder()
                .productName(product.get().getProductName())
                .category(product.get().getCategory())
                .quantity(addProductDTO.getQuantity())
                .price(product.get().getPrice())
                .cartId(cart.get())
                .build());

        handledCartItem.setQuantity(handledCartItem.getQuantity() + addProductDTO.getQuantity());
        cart.get().setTotal(cart.get().getTotal() + (long) handledCartItem.getPrice() * handledCartItem.getQuantity());

        cartRepository.save(cart.get());
        cartItemRepository.save(handledCartItem);
        productRepository.save(product.get());
    }

    public void removeCartitem(AddCartItemDTO addProductDTO){
        Optional<Product> product = productRepository.findById(addProductDTO.getProductId());

        if(product.isEmpty()){
            throw new BadRequestException("Product not founded");
        }
        product.get().setQuantity(product.get().getQuantity() - addProductDTO.getQuantity());

        if(product.get().getQuantity() < 0){
            throw new BadRequestException("Product quantity is less than the wanted quantity");
        }

        Optional<Cart> cart = cartRepository.findById(addProductDTO.getCartId());

        if(cart.isEmpty()){
            throw new BadRequestException("Cart not founded");
        }

        Optional<CartItem> cartItem = cartItemRepository.findByProductNameAndCategoryAndPrice(
                product.get().getProductName(),
                product.get().getCategory(),
                product.get().getPrice());

        CartItem handledCartItem = cartItem.orElseGet(() -> CartItem.builder()
                .productName(product.get().getProductName())
                .category(product.get().getCategory())
                .quantity(addProductDTO.getQuantity())
                .price(product.get().getPrice())
                .cartId(cart.get())
                .build());

        handledCartItem.setQuantity(handledCartItem.getQuantity() + addProductDTO.getQuantity());
        cart.get().setTotal(cart.get().getTotal() + (long) handledCartItem.getPrice() * handledCartItem.getQuantity());

        cartRepository.save(cart.get());
        cartItemRepository.save(handledCartItem);
        productRepository.save(product.get());
    }

}
