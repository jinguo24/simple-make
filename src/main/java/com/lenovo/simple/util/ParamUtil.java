package com.lenovo.simple.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lenovo.simple.configure.Container;
import com.lenovo.simple.configure.configs.Orm;
import com.lenovo.simple.configure.table.DbColumn;
import com.lenovo.simple.configure.table.Table;
import com.lenovo.simple.configure.table.TableColumn;
import com.lenovo.simple.dataSource.DataSourceInvokerFactory;
import com.lenovo.simple.dataSource.exception.DataSourceInvokerNotFoundException;
import com.lenovo.simple.orm.OrmColumnTypeFactory;
import com.lenovo.simple.orm.exception.OrmColumnTypeServiceNotFoundException;

public class ParamUtil {

	public static String getFieldType(String dataSourceType,String dbtype) {
		try {
			return DataSourceInvokerFactory.getInvoker(dataSourceType).getFieldType(dbtype);
		} catch (DataSourceInvokerNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getJavaPojoBeanContents(String dataSourceType, Table table) {
		List<String> contents = new ArrayList<String>();
		List<String> fields = new ArrayList<String>();
		List<String> setget = new ArrayList<String>();
		for (DbColumn dc:table.getDbcolumns()) {
				fields.add(getFieldString(dataSourceType,dc));
				setget.addAll(getSetGetListString(dataSourceType,dc));
		}
		contents.addAll(fields);
		contents.addAll(setget);
		return contents;
	}
	
	private static List<String> getSetGetListString(String dataSourceType,DbColumn column) {
		List<String> setget = new ArrayList<String>();
		String fieldType = getFieldType(dataSourceType,column.getDataType());
		setget.add(new StringBuffer("public void ").append(getSetMethodName(column.getColumnName()))
				.append("(").append(fieldType).append(" ").append(column.getColumnName()).append(")  {").toString());
		setget.add(new StringBuffer("  this.").append(column.getColumnName()).append(" = ")
				.append(column.getColumnName()).append(";").toString());
		setget.add("}");
		
		setget.add(new StringBuffer("public ").append(fieldType).append(" ").append(getGetMethodName(column.getColumnName(),fieldType)).append("() {").toString());
		setget.add(new StringBuffer("  return this.").append(column.getColumnName()).append(";").toString());
		setget.add("}");
		return setget;
	}
	
	private static String getFieldString(String dataSourceType,DbColumn column) {
		return new StringBuffer("private ").append(getFieldType(dataSourceType,column.getDataType()))
		.append(" ").append(column.getColumnName()).append(";//").append(column.getColumnComment()).toString();
	}
	
	
	public static String getSetMethodName(String name) {
		return "set"+name.substring(0,1).toUpperCase()+name.substring(1);
	}
	
	private static String getGetMethodName(String name,String fieldType) {
		if (fieldType.contains("Boolean") || fieldType.contains("boolean")) {
			return "is"+name.substring(0,1).toUpperCase()+name.substring(1);
		}
		return "get"+name.substring(0,1).toUpperCase()+name.substring(1);
	}
	
	//查询所有的属性
	public static Map getOrmFields(Container container,Table table,String dataSourceType) throws OrmColumnTypeServiceNotFoundException {
		List<DbColumn> columns = table.getDbcolumns();
		if ( null != columns && columns.size() > 0) {
			Map fm = new HashMap();
			List<Map<String,String>> fields = new ArrayList<Map<String,String>>();
			List<Map<String,String>> insertFields = new ArrayList<Map<String,String>>();
			for (DbColumn dc : columns) {
				Map<String,String> f = new HashMap<String,String>();
				f.put("coloum", dc.getColumnName());
				f.put("ormDbtype", OrmColumnTypeFactory.getInvoker(container.getOrm().getType()).getTypeFromDbType(dc.getDataType(), dataSourceType));
				f.put("javaType", ParamUtil.getFieldType(container.getDataSource().getType(), dc.getDataType()));
				//查询判断条件
				if (dc.isNumber()) {
					f.put("querycondition", dc.getColumnName()+">0");
				}else {
					f.put("querycondition", dc.getColumnName()+" != null ");
				}
				//查询语句，连接符
				Map<String, TableColumn> tcm = table.getColumns();
				if ( null != tcm && tcm.containsKey(dc.getColumnName())) {
					f.put("combar", tcm.get(dc.getColumnName()).getCombor());
				}else {
					f.put("combar", "=");
				}
				
				if (dc.isPk()) {
					fm.put("pk", f);
				}else {
					fields.add(f);
				}
				if (!dc.isAutoIncreate()) {
					insertFields.add(f);
				}
			}
			fm.put("fields", fields);
			fm.put("insertFields", fields);
			return fm;
		}
		return null;
	}
}
