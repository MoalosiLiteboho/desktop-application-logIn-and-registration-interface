package com.geniescode.share.model;

import java.time.LocalDate;
import java.util.Date;

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
