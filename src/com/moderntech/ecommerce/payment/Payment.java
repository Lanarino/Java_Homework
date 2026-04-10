package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;
import java.math.BigDecimal;

public class Payment {
    public static String process(PaymentMethod method, Order order) {
        String methodDesc = switch (method) {
            case CreditCardPayment ignored -> "банковской картой";
            case DigitalWalletPayment ignored -> "электронным кошельком";
            case CashOnDelivery ignored -> "наложенным платёжом";
            case OzonPayment ignored -> "через Ozon";
            case WildberriesPayment ignored -> "через Wildberries";
        };

        BigDecimal amount = order.getTotalAmount();
        System.out.printf("Обработка оплаты %s на сумму %.2f ₽...\n", methodDesc, amount);

        // Успешная оплата в 95% случаев
        if (Math.random() > 0.05) {
            return "SUCCESS";
        } else {
            return "FAILED";
        }
    }
}