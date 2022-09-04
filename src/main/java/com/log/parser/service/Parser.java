package com.log.parser.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.log.parser.model.Event;
import com.log.parser.model.LogEntry;
import com.log.parser.model.STATE;
import com.log.parser.repo.HSQLDBCreate;
import com.log.parser.repo.HSQLDBInsert;

public class Parser {
	
	private static final Logger logger = LogManager.getLogger(Parser.class);  
	
	private ConcurrentHashMap<String, LogEntry> map = new ConcurrentHashMap<String, LogEntry>();
	
	public void parse(String path) throws Exception {
		
		// File path is passed as parameter
        File file = new File(path);
        logger.info("Reading from file: "+ path);
        
        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        HSQLDBInsert example = new HSQLDBInsert();
        HSQLDBCreate create = new HSQLDBCreate();
        
        logger.info("Creating the event table, will throw Exception if already exists");
        create.createEventTable();
        
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
        	
             LogEntry entry = readEntryFromFile(st);
             logger.info("Entry from file: "+entry);
             
             if(entry != null && map.get(entry.getId()) != null) {
            	 
            	 Event event =  createEventObject(entry);
            	 logger.info("Event created from file: "+event);
            	 
            	 example.insertEvent(event);
             }else {
            	 map.put(entry.getId(), entry);
             }
        }
    }

	private Event createEventObject(LogEntry entry) {

		long startTime = 0;
		long endTime = 0;
		int duration = 0;
		String alert = "";

		if(map.get(entry.getId()).getState().equalsIgnoreCase(STATE.STARTED.name())) {
			startTime = map.get(entry.getId()).getTimestamp();
			endTime = entry.getTimestamp();
		}else {
			startTime = entry.getTimestamp();
			endTime = map.get(entry.getId()).getTimestamp();
		}

		duration = (int)(endTime-startTime);
		alert = duration > 4 ? "true":"false";
		Event event = new Event(entry.getId(), entry.getType(), entry.getHost(), duration , alert);
		return event;
	}

	private LogEntry readEntryFromFile(String st) {
		
		JSONParser jsonParser = new JSONParser();
		Object obj;
		LogEntry entry = null;
		try {
			obj = jsonParser.parse(st);

			JSONObject jsonObject = (JSONObject)obj;
			String id = (String)jsonObject.get("id");
			String state = (String)jsonObject.get("state");
			String type = (String)jsonObject.get("type");
			String host = (String)jsonObject.get("host");
			long timeStamp = (Long)jsonObject.get("timestamp");
			entry = new LogEntry(id, state, type, host, timeStamp);
		} catch (Exception e) {
			logger.error("Error while parsing entry from file: "+ e.getMessage());
		}
		return entry;
	}
	
	public static void main(String[] args) {

		logger.info("Parsing the file started");
		Parser parser = new Parser();
		if (args[0] == null || args[0].trim().isEmpty()) {
			logger.error("Please specify the path of file!");
			return;
		} else {
			try {
				parser.parse(args[0]);
			} catch (Exception e) {
				logger.error("Errro while parsing the file:"+ e.getMessage());
			}
		}
		logger.info("Parsing the file completed successfully");
	}
}
