package com.moderntech.ecommerce.models;

import java.math.BigDecimal;

public record OrderItem(String productId, String productName, int quantity, BigDecimal unitPrice) {}