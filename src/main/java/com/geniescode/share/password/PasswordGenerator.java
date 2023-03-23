package com.geniescode.share.password;

import java.util.function.BiFunction;

public class PasswordGenerator implements BiFunction<String, String, String> {
    @Override
    public String apply(String name, String surname) {
        return name + "@" + surname + "12345";
    }
}
