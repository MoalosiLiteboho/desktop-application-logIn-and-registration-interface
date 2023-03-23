package com.geniescode.dao;

import com.geniescode.signIn.SignIn;
import com.geniescode.share.model.User;
import com.geniescode.share.password.PasswordEncryptor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static com.geniescode.share.database.DatabaseConnection.connection;

public class DAOImplementation implements DAO{

    @Override
    public Optional<User> findUserByEmailAndPassword(SignIn signIn) {
        try {
            PreparedStatement statement = connection.get().prepareStatement("select Id, Name ,Surname ,Gender ,DateOfBirth ,AuthorityId ,Email ,Password, Enabled ,ExpiryDate from " +
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
                        LocalDate.parse(result.getString("DateOfBirth")),
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

    @Override
    public Optional<String> findEmailByEmail(String email) {
        try {
            PreparedStatement statement = connection.get().prepareStatement("select Email from UserAccount where Email = ?");
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            return result.next() ?
                    Optional.of(result.getString("Email")) : Optional.empty();
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }
}
