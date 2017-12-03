package com.lenovo.simple.configure.table;

public class DbColumn {
	private String columnName;
	private String dataType;
	private String columnComment;
	private boolean number;
	private boolean pk;
	private boolean autoIncreate;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public boolean isNumber() {
		return number;
	}
	public void setNumber(boolean number) {
		this.number = number;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public boolean isAutoIncreate() {
		return autoIncreate;
	}
	public void setAutoIncreate(boolean autoIncreate) {
		this.autoIncreate = autoIncreate;
	}
}
