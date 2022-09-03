package com.log.parser.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HSQLDBInsertExample {
    private static final String INSERT_PERSONS_SQL = "INSERT INTO Persons" +
        "  (PERSONID, LASTNAME, FIRSTNAME, ADDRESS, CITY) VALUES " +
        " (?, ?, ?, ?, ?);";

//    public static void main(String[] argv) throws SQLException {
//        HSQLDBInsertExample createTableExample = new HSQLDBInsertExample();
//        createTableExample.insertRecord();
//    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_PERSONS_SQL);
        // Step 1: Establishing a Connection
        try {
        	
        	Connection connection = JDBCUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSONS_SQL);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Tony");
            preparedStatement.setString(3, "tonygmail.com");
            preparedStatement.setString(4, "US");
            preparedStatement.setString(5, "secret");

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
}
