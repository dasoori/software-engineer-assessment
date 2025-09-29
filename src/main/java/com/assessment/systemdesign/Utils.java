package com.assessment.systemdesign;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Utils {
    private Utils() {}

    // Prints rounded value as a string (2 decimal places);
    // calculations elsewhere should keep full precision.
    public static String roundDouble(double d) {
        return BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
