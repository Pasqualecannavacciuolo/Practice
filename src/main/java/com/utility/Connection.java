package com.utility;

import java.io.IOException;
import java.sql.*;

public class Connection {
    private static Connection instance = null;
    private java.sql.Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ReadProperties readProperties = new ReadProperties();

    private Connection() {}

    public static Connection getInstance() {
        if(instance==null){
            instance = new Connection();
        }
        return new Connection();
    }

    public java.sql.Connection getConnection() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.readProperties.read("connection.properties");
        String _db_mysql_url = readProperties.properties.getProperty("db.mysql.url");
        String _db_url = readProperties.properties.getProperty("db.url");
        String _db_username = readProperties.properties.getProperty("db.username");
        String _db_password = readProperties.properties.getProperty("db.password");
        Class.forName(_db_mysql_url).newInstance();
        this.connection = DriverManager.getConnection(_db_url, _db_username, _db_password);
        return this.connection;
    }
}
