package repository;

import model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    public void addProduct(Product product) {
        System.out.println("The product was added!");
    }
}
