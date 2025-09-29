package com.assessment.systemdesign;

public class PayPalPaymentMethod implements PaymentMethod {

    @Override
    public void pay(double amount) {
        // Split into two installments; add 5% interest to second installment
        double installment1 = amount / 2.0;
        double installment2 = amount - installment1; // keep exact split in case of odd cents
        double secondWithInterest = installment2 * 1.05;

        System.out.println("Paying $" + Utils.roundDouble(amount) + " via PayPal using Installment Payment Plan.");
        System.out.println("Paid $" + Utils.roundDouble(installment1) + " in first installment.");
        System.out.println("Paid $" + Utils.roundDouble(secondWithInterest)
                + " in second installment with 5% interest.");
    }
}
