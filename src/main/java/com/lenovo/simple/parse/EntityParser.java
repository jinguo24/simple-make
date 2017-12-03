package com.lenovo.simple.parse;

import java.util.List;

import com.lenovo.simple.configure.Container;

public interface EntityParser {

	public List<Container> parseContainer(String file);
}
