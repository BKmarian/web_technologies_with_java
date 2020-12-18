package com.example.lab10.service;

import com.example.lab10.exception.InvalidPriceException;
import com.example.lab10.exception.NoProductFoundException;
import com.example.lab10.exception.NoStockAvailableException;
import com.example.lab10.model.OrderItem;
import com.example.lab10.model.Product;
import com.example.lab10.repository.ItemRepository;
import com.example.lab10.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    public ProductService(ProductRepository productRepository, ItemRepository itemRepository) {
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;
    }

    public void saveProduct(Product product) {
        if(product.getAvailableStock() == 0)    {
            throw new NoStockAvailableException();
        }
        if(product.getPrice() <= 0) {
            throw new InvalidPriceException();
        }
        productRepository.save(product);
    }

    public void saveProducts(List<Product> products) {
        products.forEach(product -> saveProduct(product));
    }

    public Double getOrderPrice(List<OrderItem> itemsToBeOrdered) {
        return itemsToBeOrdered.stream()
                .map(item -> item.getProduct().getPrice() * item.getQuantity())
                .reduce(0.0, Double::sum);
    }

    public void updateStockOrderedProducts(List<OrderItem> itemsToBeOrdered, int orderId) {
        itemsToBeOrdered.forEach(
                item -> {
                    productRepository.decrementStock(item.getProduct(), item.getQuantity());
                    itemRepository.save(item, orderId);
                }
        );
    }

    public List<OrderItem> buildItemDetailsToBeOrdered(List<OrderItem> items) {
        return items.stream()
                .map(
                        itemOrdered -> {
                            OrderItem item = new OrderItem();
                            var p = productRepository.getProductByName(itemOrdered.getProduct().getName());
                            if(!p.isEmpty()) {
                                item.setProduct(p.get());
                                item.setQuantity(itemOrdered.getQuantity());
                                if(itemOrdered.getQuantity() > item.getProduct().getAvailableStock())   {
                                    throw new NoStockAvailableException();
                                }
                            } else  {
                                throw new NoProductFoundException();
                            }
                            return item;
                        }
                ).collect(Collectors.toList());
    }
}
