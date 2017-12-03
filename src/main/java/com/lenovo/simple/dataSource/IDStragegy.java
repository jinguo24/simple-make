package com.lenovo.simple.dataSource;

public enum IDStragegy {

	identity("identity","自增"),assigned("assigned","赋予");
	
	private String type;
	private String name;
	
	private IDStragegy(String type,String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
