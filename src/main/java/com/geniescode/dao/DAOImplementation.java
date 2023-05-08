package com.geniescode.dao;

import com.geniescode.database.DatabaseConnection;
import com.geniescode.signIn.SignIn;
import com.geniescode.share.model.User;
import com.geniescode.share.password.PasswordEncryptor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class DAOImplementation implements DAO{
    private final DatabaseConnection databaseConnection;

    public DAOImplementation() {
        databaseConnection = new DatabaseConnection();
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(SignIn signIn) {
        try {
            PreparedStatement statement = databaseConnection.get().prepareStatement("select Id, Name ,Surname ,Gender ,DateOfBirth ,AuthorityId ,Email ,Password, Enabled ,ExpiryDate from " +
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
                        result.getInt("AuthorityId"),
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
        try {
            PreparedStatement statement = databaseConnection.get().prepareStatement("insert into User(Id, Name, Surname, Gender, DateOfBirth) values (?, ?, ?, ?, ?)");
            statement.setInt(1, user.Id());
            statement.setString(2, user.name());
            statement.setString(3, user.surname());
            statement.setString(4, user.gender());
            statement.setDate(5, Date.valueOf(user.dateOfBirth()));

            statement.executeUpdate();
            statement = databaseConnection.get().prepareStatement("insert into UserAccount(UserId, AuthorityId, Email, Password, Enabled, ExpiryDate) values (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, user.Id());
            statement.setInt(2, user.authority());
            statement.setString(3, user.email());
            statement.setString(4, new PasswordEncryptor().apply(user.password()));
            statement.setBoolean(5, user.enabled());
            statement.setDate(6, Date.valueOf(user.expiryDate()));

            statement.executeUpdate();
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Optional<String> findEmailByEmail(String email) {
        try {
            PreparedStatement statement = databaseConnection.get().prepareStatement("select Email from UserAccount where Email = ?");
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
