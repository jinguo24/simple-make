package com.lenovo.simple.orm.support;

import java.util.HashMap;
import java.util.Map;

import com.lenovo.simple.orm.OrmColumnTypeService;

public class MybatisColumnTypeServiceImpl implements OrmColumnTypeService{
	
	private static Map<String,String> mysqldbMap = new HashMap<String,String>(); 
	
	static {
		//"char","varchar","longtext","mediumtext","text","tinytext"
		mysqldbMap.put("char", "CHAR");
		mysqldbMap.put("varchar", "VARCHAR");
		mysqldbMap.put("longtext", "VARCHAR");
		mysqldbMap.put("mediumtext", "VARCHAR");
		mysqldbMap.put("text", "VARCHAR");
		mysqldbMap.put("text", "tinytext");
		//"int","mediumint","smallint","tinyint","bigint"
		mysqldbMap.put("int", "INTEGER");
		mysqldbMap.put("mediumint", "INTEGER");
		mysqldbMap.put("smallint", "INTEGER");
		mysqldbMap.put("tinyint", "INTEGER");
		mysqldbMap.put("bigint", "INTEGER");
		//"float"
		mysqldbMap.put("float", "FLOAT");
		//double,
		mysqldbMap.put("double", "DOUBLE");
		//numeric,
		mysqldbMap.put("numeric", "NUMERIC");
		//decimal,
		mysqldbMap.put("decimal", "DECIMAL");
		//"date","datetime","time","timestamp","year"
		mysqldbMap.put("date", "DATE");
		mysqldbMap.put("datetime", "TIMESTAMP");
	}
	
	

	@Override
	public String getTypeFromDbType(String dbType,String dataSourceType) {
		if ("mysql".endsWith(dataSourceType)) {
			return mysqldbMap.get(dbType);
		}
		return null;
	}

}
