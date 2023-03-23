package com.geniescode.date;

import java.time.LocalDate;
import java.util.function.Supplier;

public class ExpiryDateGenerator implements Supplier<LocalDate> {
    @Override
    public LocalDate get() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.plusMonths(6);
    }
}
