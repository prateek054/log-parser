package com.log.parser.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Parser {
	
	public static void main(String[] args) {
		
		System.out.println("Created new maven project:");
		
		Parser parser = new Parser();
		
		 if (args[0] == null || args[0].trim().isEmpty()) {
		        System.out.println("You need to specify a path!");
		        return;
		    } else {
		        try {
					parser.parse(args[0]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		
		
	}

	private void parse(String path) throws Exception {
		
		// File path is passed as parameter
        File file = new File(path);
        
        JSONParser p1 = new JSONParser();
 
        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        HSQLDBInsertExample example = new HSQLDBInsertExample();
        
        example.insertRecord();
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
        	
        	  // Print the string
//            System.out.println(st);
            
            Object obj =  p1.parse(st);
            
             JSONObject jsonObject = (JSONObject)obj;
             
             String id = (String)jsonObject.get("id");
             String state = (String)jsonObject.get("state");
             long timeStamp = (Long)jsonObject.get("timestamp");
             
             System.out.println("Id: "+ id+"   state: "+state+"   stamp: "+ timeStamp);
             
        }
        	
        	
 
          
    }
		
}
