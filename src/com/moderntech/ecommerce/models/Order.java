package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.OrderStatus;
import java.math.BigDecimal;
import java.util.*;

public class Order {
    private final String orderId;
    private final List<OrderItem> orderItems;
    private final BigDecimal totalAmount;
    private OrderStatus status;
    private String paymentStatus = "PENDING";

    public Order(String orderId, List<CartItem> cartItems, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.orderItems = cartItems.stream()
            .map(item -> new OrderItem(
                item.product().id(),
                item.product().name(),
                item.quantity(),
                item.product().price()))
            .toList();
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PENDING;
    }

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
        System.out.println("Статус заказа изменён на: CONFIRMED");
    }

    public void setPaymentStatus(String status) {
        this.paymentStatus = status;
    }

    public void displaySummary() {
        System.out.println("\nИТОГОВАЯ СВОДКА");
        System.out.println("Заказ: " + orderId);
        for (var item : orderItems) {
            System.out.printf("  %s × %d @ %.2f ₽\n",
                item.productName(), item.quantity(), item.unitPrice());
        }
        System.out.printf("Итого: %.2f ₽\n", totalAmount);
        System.out.println("Статус заказа: " + status);
        System.out.println("Статус оплаты: " + paymentStatus);
    }

    public String getOrderId() { return orderId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
}