package com.example.lab10.service;

import com.example.lab10.dto.ItemDto;
import com.example.lab10.dto.OrderDto;
import com.example.lab10.mapper.OrderMapper;
import com.example.lab10.model.Order;
import com.example.lab10.model.OrderItem;
import com.example.lab10.model.Product;
import com.example.lab10.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.lab10.model.Status.ACTIVE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private InvoiceService invoiceService;
    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderService orderService;

    @Test
    @DisplayName("Save order valid")
    public void saveValidOrder()    {
        List<OrderItem> itemsWanted = getWantedOrderItems();
        List<OrderItem> itemsToBeOrdered = getItemsToBeOrdered();

        Order order = new Order();
        order.setStatus(ACTIVE);
        double price = 80;
        order.setTotalPrice(price);

        ItemDto itemDto = new ItemDto();
        itemDto.setQuantityOrdered(1);
        itemDto.setPricePerItem(price);
        itemDto.setName("dress");

        OrderDto orderDto = OrderDto.builder()
                .status(ACTIVE)
                .totalPrice(price)
                .products(List.of(itemDto))
                .build();

        when(productService.buildItemDetailsToBeOrdered(itemsWanted)).thenReturn(itemsToBeOrdered);
        when(productService.getOrderPrice(itemsToBeOrdered)).thenReturn(price);
        when(orderRepository.save(order)).thenReturn(0L);
        when(orderMapper.toDto(order,itemsToBeOrdered)).thenReturn(orderDto);
        when(invoiceService.sendInvoice(orderDto)).thenReturn(true);

        OrderDto orderDtoActual = orderService.save(itemsWanted);

        Assertions.assertEquals(orderDto, orderDtoActual);

        verify(productService).buildItemDetailsToBeOrdered(itemsWanted);
        verify(productService).getOrderPrice(itemsToBeOrdered);
        verify(orderRepository).save(order);
        verify(productService).updateStockOrderedProducts(itemsToBeOrdered, 0);
        verify(orderMapper).toDto(order, itemsToBeOrdered);
        verify(invoiceService).sendInvoice(orderDto);
    }


    private List<OrderItem> getWantedOrderItems() {
        OrderItem item1 = new OrderItem();
        item1.setProduct(new Product("dress"));
        item1.setQuantity(1);

        return List.of(item1);
    }

    private List<OrderItem> getItemsToBeOrdered() {
        OrderItem item1 = new OrderItem();
        Product product = new Product("dress");
        product.setPrice(80);
        product.setAvailableStock(20);
        item1.setProduct(product);
        item1.setQuantity(1);

        return List.of(item1);
    }
}