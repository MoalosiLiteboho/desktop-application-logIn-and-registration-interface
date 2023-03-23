package com.geniescode.date;

import java.time.LocalDate;
import java.util.function.Supplier;

public class ExpiryDateGenerator implements Supplier<LocalDate> {
    @Override
    public LocalDate get() {
        return LocalDate.now()
                .plusMonths(6);
    }
}
