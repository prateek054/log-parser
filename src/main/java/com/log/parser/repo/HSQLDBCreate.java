package com.log.parser.repo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.log.parser.util.JDBCUtils;

public class HSQLDBCreate {
    
    private static final String createEventTableSQL = "CREATE TABLE EVENT (id  varchar(20) NOT NULL," +
            "  duration INT, type varchar(20), host varchar(10),  alert varchar(10) , PRIMARY KEY (id));";
    
    private static final Logger logger = LogManager.getLogger(HSQLDBCreate.class);  

    public void createEventTable() {

        logger.info(createEventTableSQL);
        // Step 1: Establishing a Connection
        try {
        	Connection connection = JDBCUtils.getConnection();
            // Step 2:Create a statement using connection object
            Statement statement = connection.createStatement();

            // Step 3: Execute the query or update query
            statement.execute(createEventTableSQL);
        } catch (SQLException e) {
            // print SQL exception information
        	logger.error("Error in creating table: "+ e.getMessage());
        }
    }
}
