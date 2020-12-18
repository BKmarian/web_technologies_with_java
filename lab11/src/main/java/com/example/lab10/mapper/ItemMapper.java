package com.example.lab10.mapper;

import com.example.lab10.dto.ItemDto;
import com.example.lab10.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDto toDto(OrderItem itemOrdered)  {
        ItemDto itemDto = ItemDto.builder()
                        .name(itemOrdered.getProduct().getName())
                        .pricePerItem(itemOrdered.getProduct().getPrice())
                        .quantityOrdered(itemOrdered.getQuantity())
                        .build();

        return itemDto;
    }
}
