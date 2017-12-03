package com.lenovo.simple.configure.table;

import java.util.List;
import java.util.Map;

public class Table {
	private String name;
	private Map<String,TableColumn> columns;
	private List<DbColumn> dbcolumns;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, TableColumn> getColumns() {
		return columns;
	}
	public void setColumns(Map<String, TableColumn> columns) {
		this.columns = columns;
	}
	public List<DbColumn> getDbcolumns() {
		return dbcolumns;
	}
	public void setDbcolumns(List<DbColumn> dbcolumns) {
		this.dbcolumns = dbcolumns;
	}
	public String getBeanName() {
		//首字母大写
		char[] cs=this.name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
	}
}
