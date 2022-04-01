package com.utility;

import java.io.IOException;
import java.sql.*;

// This have the behaviour of a SINGLETON
public class Connection {
    private static Connection instance = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private final ReadProperties readProperties = new ReadProperties();

    private Connection() {}

    public static Connection getInstance() {
        if(instance==null){
            instance = new Connection();
        }
        return new Connection();
    }

    // This method let you connect to the database
    public java.sql.Connection getConnection() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        // Reading from a properties file to prevent SQLINJECTION
        this.readProperties.read("connection.properties");

        String _db_mysql_url = readProperties.properties.getProperty("db.mysql.url");
        String _db_url = readProperties.properties.getProperty("db.url");
        String _db_username = readProperties.properties.getProperty("db.username");
        String _db_password = readProperties.properties.getProperty("db.password");

        Class.forName(_db_mysql_url).newInstance();

        return DriverManager.getConnection(_db_url, _db_username, _db_password);
    }

    // This method link the statement to the connection
    public void initStatement() throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        statement = this.getConnection().createStatement();
    }

    /*
     * This method execute a query
     * Better suited for SELECT operations
     */
    public void executeStatementSelect(String query) throws SQLException{
        statement.execute(query);
    }

    /*
     * This method execute a query
     * Better suited for INSERT - UPDATE - DELETE operations
     */
    public java.sql.Statement executeStatementUpdate(String query) throws SQLException {
        statement.executeUpdate(query);
        return statement;
    }

    public java.sql.PreparedStatement preparePreparedStatement(String query) throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        preparedStatement = this.getConnection().prepareStatement(query);
        return preparedStatement;
    }

    public void executePreparedStatement() throws SQLException {
        this.preparedStatement.executeUpdate();
    }
}
