package com.geniescode.share.password;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class PasswordEncryptor implements Function<String, String> {
    @Override
    public String apply(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}