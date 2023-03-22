package com.geniescode.dao;

import com.geniescode.model.Credentials;
import com.geniescode.database.DatabaseConnection;
import com.geniescode.model.User;
import com.geniescode.passwordEncryption.PasswordHashGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DAOImplementation implements DAO{

    @Override
    public Optional<User> findUserByEmailAndPassword(Credentials credentials) {
        try {
            PreparedStatement statement = DatabaseConnection.connection.get().prepareStatement("select Id, Name ,Surname ,Gender ,DateOfBirth ,AuthorityId ,Email ,Password, Enabled ,ExpiryDate from " +
                    "(select * from User user inner join UserAccount account on user.Id = account.UserId) " +
                    "as UserTable where Email = ? and Password = ?");
            statement.setString(1, credentials.email());
            statement.setString(2, new PasswordHashGenerator().apply(credentials.password()));

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
                        result.getDate("ExpiryDate"),
                        result.getBoolean("Enabled"),
                        result.getString("Password")
                ));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }
}
