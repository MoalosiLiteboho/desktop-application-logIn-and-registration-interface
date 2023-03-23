package com.geniescode.email;

import com.geniescode.dao.DAOImplementation;

import java.util.function.Predicate;

public class EmailTaken implements Predicate<String> {
    @Override
    public boolean test(String email) {
        return new DAOImplementation()
                .findEmailByEmail(email)
                .isPresent();
    }
}