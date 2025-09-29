package com.assessment.systemdesign;

public class CreditCardPaymentMethod implements PaymentMethod {

    @Override
    public void pay(double amount) {
        // Redeem 10% of amount, capped at $10
        double redeemable = Math.min(amount * 0.10, 10.0);
        double remaining  = amount - redeemable;

        System.out.println("Paying $" + Utils.roundDouble(amount)
                + " via Credit Card using Reward Points Redemption Feature.");
        System.out.println("Redeemed $" + Utils.roundDouble(redeemable) + " using reward points.");
        System.out.println("Paying remaining amount of $" + Utils.roundDouble(remaining) + " via credit card.");
    }
}
