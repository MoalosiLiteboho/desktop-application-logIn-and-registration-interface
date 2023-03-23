package com.geniescode.date;

import java.util.Date;
import java.util.function.Predicate;

public class DateValidator implements Predicate<Date> {
    @Override
    public boolean test(Date date) {
        return false;
    }
}
