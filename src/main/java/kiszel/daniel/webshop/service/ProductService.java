package kiszel.daniel.webshop.service;

import kiszel.daniel.webshop.model.Product;
import kiszel.daniel.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
}
