package com.geniescode.share.model;

import java.time.LocalDate;

public record User(
        int Id,
        String name,
        String surname,
        String gender,
        LocalDate dateOfBirth,
        String email,
        String authority,
        LocalDate expiryDate,
        boolean enabled,
        String password
) {
}
