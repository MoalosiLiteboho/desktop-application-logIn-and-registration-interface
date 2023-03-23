package com.geniescode.signUp;

import java.time.LocalDate;

public record SignUp(
        String name,
        String surname,
        String gender,
        LocalDate dateOfBirth,
        String email
) {
}
