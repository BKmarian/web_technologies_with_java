package com.example.lab10.dto;

import com.example.lab10.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int id;
    private double totalPrice;
    private List<ItemDto> products;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return id == orderDto.id &&
                Double.compare(orderDto.totalPrice, totalPrice) == 0 &&
                Objects.equals(products, orderDto.products) &&
                status == orderDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, products, status);
    }
}
