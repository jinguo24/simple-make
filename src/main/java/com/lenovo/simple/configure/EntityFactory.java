package com.lenovo.simple.configure;

import java.io.File;
import java.util.List;

import com.lenovo.simple.parse.EntityParserRegister;
import com.lenovo.simple.parse.exception.SimpleFileNotValidException;
import com.lenovo.simple.parse.exception.SimpleParseException;
import com.lenovo.simple.parse.exception.SimpleParserNotFoundException;

public class EntityFactory {

	public static List<Container> parseContainer(String file) throws SimpleFileNotValidException, SimpleParserNotFoundException, SimpleParseException {
		File f = new File(file);
		if (!f.getName().contains(".")) {
			throw new SimpleFileNotValidException(" file :"+file + " should contain \".\" . " );
		}
		String prefix = f.getName().substring(f.getName().lastIndexOf(".")+1);
		return EntityParserRegister.getParser(prefix).parseContainer(file);
		
	}
}
