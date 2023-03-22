package com.geniescode.model;


import java.sql.Date;

public record User(
        int Id,
        String name,
        String surname,
        String gender,
        String dateOfBirth,
        String email,
        String authority,
        Date expiryDate,
        boolean enabled
) {
}
