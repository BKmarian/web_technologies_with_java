package com.example.lab10.mapper;

import com.example.lab10.dto.ItemDto;
import com.example.lab10.dto.OrderDto;
import com.example.lab10.model.Order;
import com.example.lab10.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ItemMapper itemMapper;

    public OrderMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public OrderDto toDto(Order order, List<OrderItem> orderItems)  {
        OrderDto orderDto = OrderDto.builder()
                            .id(order.getId())
                            .status(order.getStatus())
                            .totalPrice(order.getTotalPrice())
                            .build();

        List <ItemDto> itemDtos = orderItems.stream()
                .map(i -> itemMapper.toDto(i))
                .collect(Collectors.toList());

        orderDto.setProducts(itemDtos);

        return orderDto;
    }
}
