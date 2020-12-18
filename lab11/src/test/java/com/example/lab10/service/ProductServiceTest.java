package com.example.lab10.service;

import com.example.lab10.exception.InvalidPriceException;
import com.example.lab10.exception.NoStockAvailableException;
import com.example.lab10.model.OrderItem;
import com.example.lab10.model.Product;
import com.example.lab10.repository.ItemRepository;
import com.example.lab10.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ProductService productService;

    private List<OrderItem> defaultItemsToBeOrdered;

    @BeforeEach
    private void setupOrderItems()   {
        OrderItem item1 = new OrderItem();
        Product product1 = new Product();
        product1.setPrice(120);
        item1.setProduct(product1);
        item1.setQuantity(1);

        OrderItem item2 = new OrderItem();
        Product product2 = new Product();
        product2.setPrice(40);
        item2.setProduct(product2);
        item2.setQuantity(5);

        defaultItemsToBeOrdered = List.of(item1, item2);
    }

    @BeforeAll
    public static void setupBeforeAll()    {
    }

    @AfterEach
    public void afterEach() {

    }

    @AfterAll
    public static void afterAll() {

    }

    @Test
    @DisplayName("Save product with valid fields")
    public void saveProductValid()  {
        Product product = new Product();
        product.setAvailableStock(10);
        product.setPrice(100);

        productService.saveProduct(product);

      //  verify(productRepository, times(1)).save(product);
        verify(productRepository).save(product);
    }

    @Test
    public void saveProductWithNoStockAvailable()   {
        Product product = new Product();
        product.setAvailableStock(0);

        NoStockAvailableException exception = assertThrows( NoStockAvailableException.class,
                () -> productService.saveProduct(product));

        assertEquals("No stock available!", exception.getMessage());
    }

    @Test
    public void saveProductWithInvalidPrice()   {
        Product product = new Product();
        product.setAvailableStock(10);
        product.setPrice(0);

        InvalidPriceException exception = assertThrows( InvalidPriceException.class,
                () -> productService.saveProduct(product));

        assertEquals("The given price is not valid!", exception.getMessage());
    }

    @Test
    public void saveValidProducts() {
        Product product1 = new Product();
        product1.setAvailableStock(10);
        product1.setPrice(10);

        Product product2 = new Product();
        product2.setAvailableStock(56);
        product2.setPrice(45);

        List<Product> products = List.of(product1, product2);

        productService.saveProducts(products);

        for(Product product: products)  {
            verify(productRepository).save(product);
        }
    }

    @Test
    @DisplayName("Test compute total price for order")
    public void testTotalPrice()    {
        Double actual = productService.getOrderPrice(defaultItemsToBeOrdered);
        assertEquals(320, actual);
    }

    @Test
    public void testUpdateStockOrderedProducts()    {
        int orderId= 5;
        productService.updateStockOrderedProducts(defaultItemsToBeOrdered, orderId);

        for(OrderItem item: defaultItemsToBeOrdered)   {
            verify(productRepository).decrementStock(item.getProduct(), item.getQuantity());
            verify(itemRepository).save(item, orderId);
        }

    }

}