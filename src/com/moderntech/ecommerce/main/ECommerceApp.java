package com.moderntech.ecommerce.main;

import com.moderntech.ecommerce.enums.ProductCategory;
import com.moderntech.ecommerce.models.*;
import com.moderntech.ecommerce.payment.*;

import java.math.BigDecimal;

public class ECommerceApp {
    public static void main(String[] args) {
        // Создаём несколько товаров
        Product phone = new Product("1", "iPhone 17 pro max", ProductCategory.SMARTPHONE, new BigDecimal("109990"), 10);
        Product tablet = new Product("2", "Samsung Tab S10", ProductCategory.TABLET, new BigDecimal("65000"), 7);
        Product headphones = new Product("3", "AirPods Pro 2", ProductCategory.ACCESSORY, new BigDecimal("24990"), 15);
        Product camera = new Product("4", "Canon EOS R50", ProductCategory.CAMERA, new BigDecimal("89990"), 3);

        System.out.println("Каталог:");
        System.out.println("- " + phone.name() + " за " + phone.price() + " руб");
        System.out.println("- " + tablet.name() + " за " + tablet.price() + " руб");
        System.out.println("- " + headphones.name() + " за " + headphones.price() + " руб");
        System.out.println("- " + camera.name() + " за " + camera.price() + " руб");

        Customer customer = new Customer("Иван Петров", "ivan.p@example.com");
        System.out.println("\nПокупатель: " + customer.getName() + " (" + customer.getEmail() + ")");

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(phone, 1);
        cart.addProduct(headphones, 1);
        cart.display();

        // Создаём заказ
        Order order = new Order("ORD-2025-001", cart.getItems(), cart.getTotalWithVat());
        System.out.println("\nОформляем заказ:");
        System.out.println("Номер заказа: " + order.getOrderId());
        System.out.println("Сумма к оплате: " + order.getTotalAmount() + " руб");
        order.confirm(); // меняем статус

        // Три сценария оплаты
        System.out.println("\n--- Сценарий 1: Ozon + карта ---");
        String res1 = Payment.process(new OzonPayment(), order);
        System.out.println("Оплата прошла: " + res1);

        System.out.println("\n--- Сценарий 2: Wildberries + кошелёк ---");
        String res2 = Payment.process(new WildberriesPayment(), order);
        System.out.println("Оплата прошла: " + res2);

        System.out.println("\n--- Сценарий 3: Ozon + наложка ---");
        String res3 = Payment.process(new CashOnDelivery(), order);
        System.out.println("Оплата прошла: " + res3);

        // Итог
        order.setPaymentStatus(res3);
        order.displaySummary();

    }
}