package com.example.lab10.service;

import com.example.lab10.dto.OrderDto;
import com.example.lab10.model.Status;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

      public boolean sendInvoice(OrderDto orderDto)    {
        if(orderDto == null)    {
            throw new RuntimeException("Order is null!");
        }
        if(orderDto.getStatus() == Status.CANCELLED)    {
            throw new RuntimeException("The order is CANCELLED");
        }

        if(orderDto.getTotalPrice() >= 600) {
            System.out.println("you received a code for a 10% discount on your next purchase!");
            return true;
        }

        return false;
    }

}
