package com.lenovo.simple.parse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lenovo.simple.parse.exception.SimpleParserNotFoundException;
import com.lenovo.simple.parse.impl.DefaultJsonParser;
import com.lenovo.simple.parse.impl.DefaultXmlParser;

public class EntityParserRegister {
	private static Map<String,EntityParser> defaultParserHolder = new ConcurrentHashMap<String,EntityParser>();
	private static Map<String,EntityParser> userParserHolder = new ConcurrentHashMap<String,EntityParser>();
	static {
		setDefaultParser("xml",new DefaultXmlParser());
		setDefaultParser("json",new DefaultJsonParser());
	}
	
	public static EntityParser getParser(String type) throws SimpleParserNotFoundException {
		if (userParserHolder.containsKey(type) ) {
			return userParserHolder.get(type);
		}
		if (defaultParserHolder.containsKey(type) ) {
			return defaultParserHolder.get(type);
		}
		throw new SimpleParserNotFoundException(" fileType ["+type+"] not supported. ");
	}
	
	private static void setDefaultParser(String type,EntityParser parser) {
		defaultParserHolder.put(type, parser);
	}
	
	public static void setParser(String type,EntityParser parser) {
		userParserHolder.put(type, parser);
	}
}
