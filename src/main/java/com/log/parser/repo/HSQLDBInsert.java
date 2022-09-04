package com.log.parser.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.log.parser.model.Event;
import com.log.parser.util.JDBCUtils;

public class HSQLDBInsert {
    
    private static final String INSERT_EVENT_SQL = "INSERT INTO EVENT" +
            "  (ID, DURATION, TYPE, HOST, ALERT) VALUES " +
            " (?, ?, ?, ?, ?);";
    
    private static final Logger logger = LogManager.getLogger(HSQLDBInsert.class); 
    
    public void insertEvent(Event event) throws SQLException {
        // Step 1: Establishing a Connection
        try {
        	Connection connection = JDBCUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_SQL);
            preparedStatement.setString(1, event.getEventId());
            preparedStatement.setInt(2, event.getDuration());
            preparedStatement.setString(3, event.getType());
            preparedStatement.setString(4, event.getHost());
            preparedStatement.setString(5, event.getAlert());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while inserting event in db: "+ e.getMessage());
        }
        
        logger.info("Event inserted successfully in db :"+ event);
    }
}
