package com.geniescode.signUp;

import java.sql.Date;
import java.time.LocalDate;

public record SignUp(
        String name,
        String surname,
        String gender,
        LocalDate dateOfBirth,
        String email
) {
}
