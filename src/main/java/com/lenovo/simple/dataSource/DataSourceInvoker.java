package com.lenovo.simple.dataSource;

import java.util.List;

import com.lenovo.simple.configure.DataSource;
import com.lenovo.simple.configure.table.DbColumn;
import com.lenovo.simple.configure.table.Table;

public interface DataSourceInvoker {

	public List<DbColumn> queryTable(DataSource datasource,Table table);
	
	public String getFieldType(String dbtype);
}
