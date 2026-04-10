package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.ProductCategory;
import java.math.BigDecimal;

public record Product(
    String id,
    String name,
    ProductCategory category,
    BigDecimal price,
    int stock
) {}