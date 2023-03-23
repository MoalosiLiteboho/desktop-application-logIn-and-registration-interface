package com.geniescode.date;

import java.time.LocalDate;
import java.util.function.Predicate;

public class DateValidator implements Predicate<LocalDate> {
    @Override
    public boolean test(LocalDate date) {
        return !date.equals(LocalDate.now())
                && date.isBefore(LocalDate.now().minusYears(15));
    }
}
