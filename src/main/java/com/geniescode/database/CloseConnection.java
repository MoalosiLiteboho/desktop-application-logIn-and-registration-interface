package com.geniescode.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

public class CloseConnection implements Consumer<Connection> {
    public void accept(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            }catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
