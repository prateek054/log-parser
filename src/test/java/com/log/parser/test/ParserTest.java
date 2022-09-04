package com.log.parser.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.log.parser.service.Parser;

public class ParserTest {
	
	private static Parser parser;
	
	@BeforeClass
    public static void initParser() {
        parser = new Parser();
    }
 
    @Test(expected = Exception.class)
    public void testPathNull() throws Exception {
    	parser.parse(null);
    }

}
