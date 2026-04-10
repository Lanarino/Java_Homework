package com.moderntech.ecommerce.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ShoppingCart {
    private final Map<String, CartItem> items = new LinkedHashMap<>();

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) return;
        if (product.stock() < quantity) {
            System.out.println("Недостаточно товара: " + product.name());
            return;
        }
        items.merge(product.id(), new CartItem(product, quantity),
            (old, newItem) -> new CartItem(product, old.quantity() + newItem.quantity()));
    }

    public BigDecimal getTotalWithVat() {
        BigDecimal total = items.values().stream()
            .map(CartItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.multiply(BigDecimal.valueOf(1.2)).setScale(2, RoundingMode.HALF_UP);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public void display() {
        System.out.println("\n🛒 Корзина:");
        if (items.isEmpty()) {
            System.out.println("Пусто.");
            return;
        }
        for (var item : items.values()) {
            System.out.printf("  %s × %d = %.2f ₽\n",
                item.product().name(), item.quantity(), item.getTotalPrice());
        }
        System.out.printf("Итого с НДС (20%%): %.2f ₽\n", getTotalWithVat());
    }
}
