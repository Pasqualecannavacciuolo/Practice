package com.automobile;

import com.utility.Connection;
import com.utility.ReadProperties;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBOperations {
    Auto auto = new Auto("Audi", "DE");
    Connection conn = Connection.getInstance();
    ReadProperties rd = new ReadProperties();

    // This method creates a table if not exists in the database
    public void createTable() throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        rd.read("queries.properties");
        String sql = rd.properties.getProperty("db.create");
        conn.initStatement();
        conn.executeStatementSelect(sql);
    }

    // This method insert data in the table
    public void insertIntoTable() throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        PreparedStatement ps = null;
        String brand, nationality;
        brand = auto.getBrand();
        nationality = auto.getNationality();

        String sql = rd.properties.getProperty("db.insert");

        ps = conn.preparePreparedStatement(sql);
        ps.setString(1, brand);
        ps.setString(2, nationality);

        conn.executePreparedStatement();

    }
}
