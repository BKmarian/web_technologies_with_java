package com.example.lab10.service;

import com.example.lab10.dto.OrderDto;
import com.example.lab10.exception.NoProductFoundException;
import com.example.lab10.mapper.OrderMapper;
import com.example.lab10.model.Order;
import com.example.lab10.model.OrderItem;
import com.example.lab10.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.lab10.model.Status.ACTIVE;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InvoiceService invoiceService;
    private final ProductService productService;


    public OrderDto save(List<OrderItem> itemsWanted)   {
        List<OrderItem> itemsToBeOrdered = productService.buildItemDetailsToBeOrdered(itemsWanted);

        if(itemsWanted.size() != itemsToBeOrdered.size()) {
            throw new NoProductFoundException();
        }

        Order order = new Order();
        order.setStatus(ACTIVE);
        order.setTotalPrice(productService.getOrderPrice(itemsToBeOrdered));
        int orderId =  (int) orderRepository.save(order);
        productService.updateStockOrderedProducts(itemsToBeOrdered, orderId);
        order.setId(orderId);

        OrderDto orderDto = orderMapper.toDto(order, itemsToBeOrdered);
        invoiceService.sendInvoice(orderDto);

        return  orderDto;
    }

}
