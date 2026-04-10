package com.moderntech.ecommerce.payment;

public sealed interface PaymentMethod permits
    CreditCardPayment,
    DigitalWalletPayment,
    CashOnDelivery,
    OzonPayment,
    WildberriesPayment {}