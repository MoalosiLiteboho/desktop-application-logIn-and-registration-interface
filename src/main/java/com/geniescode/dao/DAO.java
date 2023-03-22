package com.geniescode.dao;

import com.geniescode.model.Credentials;
import com.geniescode.model.User;

import java.util.Optional;

public interface DAO {
    Optional<User> findUserByEmailAndPassword(Credentials credentials);
}
