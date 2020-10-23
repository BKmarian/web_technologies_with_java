package service;

import model.Product;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(String name, double price)   {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        productRepository.addProduct(p);
    }
}
