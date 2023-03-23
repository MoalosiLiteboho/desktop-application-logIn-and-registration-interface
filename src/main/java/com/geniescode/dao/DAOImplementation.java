package com.geniescode.dao;

import com.geniescode.signIn.SignIn;
import com.geniescode.share.database.DatabaseConnection;
import com.geniescode.share.model.User;
import com.geniescode.share.password.PasswordEncryptor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DAOImplementation implements DAO{

    @Override
    public Optional<User> findUserByEmailAndPassword(SignIn signIn) {
        try {
            PreparedStatement statement = DatabaseConnection.connection.get().prepareStatement("select Id, Name ,Surname ,Gender ,DateOfBirth ,AuthorityId ,Email ,Password, Enabled ,ExpiryDate from " +
                    "(select * from User user inner join UserAccount account on user.Id = account.UserId) " +
                    "as UserTable where Email = ? and Password = ?");
            statement.setString(1, signIn.email());
            statement.setString(2, new PasswordEncryptor().apply(signIn.password()));

            ResultSet result = statement.executeQuery();

            if (result.next())
                return Optional.of(new User(
                        result.getInt("Id"),
                        result.getString("Name"),
                        result.getString("Surname"),
                        result.getString("Gender"),
                        result.getString("DateOfBirth"),
                        result.getString("Email"),
                        result.getString("AuthorityId"),
                        result.getDate("ExpiryDate").toLocalDate(),
                        result.getBoolean("Enabled"),
                        result.getString("Password")
                ));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void saveUser(User user) {
        System.out.println(
                user.toString()
        );
    }
}
