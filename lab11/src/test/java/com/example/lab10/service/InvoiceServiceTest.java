package com.example.lab10.service;

import com.example.lab10.dto.OrderDto;
import com.example.lab10.model.Order;
import com.example.lab10.model.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvoiceServiceTest {

    private InvoiceService invoiceService = new InvoiceService();

    @Test
    @DisplayName("sendInvoice when order is null")
    public void sendInvoiceWithOrderNull()  {
        OrderDto orderDto = null;

        RuntimeException exception = assertThrows( RuntimeException.class,
                () -> invoiceService.sendInvoice(orderDto));

        assertEquals("Order is null!", exception.getMessage());
    }

    @Test
    public void sendInvoiceWithStatusCancelled()    {
        OrderDto orderDto = new OrderDto();
        orderDto.setStatus(Status.CANCELLED);

        RuntimeException exception = assertThrows( RuntimeException.class,
                () -> invoiceService.sendInvoice(orderDto));

        assertEquals("The order is CANCELLED", exception.getMessage());
    }

//    @Test
//    public void sendInvoiceWithoutDiscount(){
//        OrderDto orderDto = new OrderDto();
//        orderDto.setTotalPrice(500);
//
//        boolean actualResult = invoiceService.sendInvoice(orderDto);
//        assertEquals(false, actualResult);
//    }


    private static Stream<Arguments> getParametersForDiscountInvoice()  {
        OrderDto orderDto = new OrderDto();
        orderDto.setTotalPrice(100);

        OrderDto orderDto2 = new OrderDto();
        orderDto2.setTotalPrice(200);

        OrderDto orderDto3 = new OrderDto();
        orderDto3.setTotalPrice(300);

        return Stream.of(
                Arguments.of(orderDto),
                Arguments.of(orderDto2),
                Arguments.of(orderDto3)
        );
    }

    @ParameterizedTest
    @MethodSource("getParametersForDiscountInvoice")
    public void testinvoiceWithoutDiscount(OrderDto orderDto)    {
        boolean actualResult = invoiceService.sendInvoice(orderDto);
        assertEquals(false, actualResult);
    }


    @Test
    public void sendInvoiceWithDiscount(){
        OrderDto orderDto = new OrderDto();
        orderDto.setTotalPrice(700);

        boolean actualResult = invoiceService.sendInvoice(orderDto);
        assertEquals(true, actualResult);
    }

}