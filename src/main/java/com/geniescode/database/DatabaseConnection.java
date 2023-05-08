package com.geniescode.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Supplier;

public class DatabaseConnection implements Supplier<Connection> {
    public Connection get() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalSystemDesktopApplication", "root", "");
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
