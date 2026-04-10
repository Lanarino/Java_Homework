package com.moderntech.ecommerce.models;

import java.math.BigDecimal;

public record CartItem(Product product, int quantity) {
    public BigDecimal getTotalPrice() {
        return product.price().multiply(BigDecimal.valueOf(quantity));
    }
}