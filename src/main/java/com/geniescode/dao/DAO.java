package com.geniescode.dao;

import com.geniescode.signIn.SignIn;
import com.geniescode.share.model.User;

import java.util.Optional;

public interface DAO {
    Optional<User> findUserByEmailAndPassword(SignIn signIn);
    void saveUser(User user);
}
