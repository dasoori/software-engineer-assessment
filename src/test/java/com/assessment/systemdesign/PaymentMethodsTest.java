package com.assessment.systemdesign;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentMethodsTest {

    @Test
    void creditCard_PrintFormatAndMath() {
        PaymentMethod cc = new CreditCardPaymentMethod();
        double amount = 95.75; // 10% = 9.575 -> cap is $10, so 9.58 rounds (printed), remaining ~ 86.175 -> 86.18 printed

        String out = captureStdout(() -> cc.pay(amount));

        assertTrue(out.contains("Paying $95.75 via Credit Card using Reward Points Redemption Feature."));
        assertTrue(out.contains("Redeemed $9.58 using reward points."));
        assertTrue(out.contains("Paying remaining amount of $86.17".replace("$86.17", "$86.18"))); // rounding validation
    }

    @Test
    void creditCard_CapAtTenDollars() {
        PaymentMethod cc = new CreditCardPaymentMethod();
        double amount = 500.00; // 10% = 50.00 -> cap at $10
        String out = captureStdout(() -> cc.pay(amount));
        assertTrue(out.contains("Redeemed $10.00 using reward points."));
        assertTrue(out.contains("Paying remaining amount of $490.00 via credit card."));
    }

    @Test
    void paypal_TwoInstallmentsWithInterest() {
        PaymentMethod pp = new PayPalPaymentMethod();
        double amount = 200.00;
        String out = captureStdout(() -> pp.pay(amount));
        assertTrue(out.contains("Paying $200.00 via PayPal using Installment Payment Plan."));
        assertTrue(out.contains("Paid $100.00 in first installment."));
        assertTrue(out.contains("Paid $105.00 in second installment with 5% interest."));
    }

    // helper
    private String captureStdout(Runnable r) {
        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            r.run();
        } finally {
            System.setOut(old);
        }
        return baos.toString();
    }
}
