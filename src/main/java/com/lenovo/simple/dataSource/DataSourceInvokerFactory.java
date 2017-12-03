package com.lenovo.simple.dataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lenovo.simple.dataSource.exception.DataSourceInvokerNotFoundException;
import com.lenovo.simple.dataSource.support.DefaultMysqlInvoker;
import com.lenovo.simple.dataSource.support.DefaultSqlServerInvoker;

public class DataSourceInvokerFactory {

	private static Map<String,DataSourceInvoker> defaultInvokerHolder = new ConcurrentHashMap<String,DataSourceInvoker>();
	private static Map<String,DataSourceInvoker> userInvokerHolder = new ConcurrentHashMap<String,DataSourceInvoker>();
	
	static {
		setDefaultInvoker("mysql",new DefaultMysqlInvoker());
		setDefaultInvoker("sqlserver",new DefaultSqlServerInvoker());
	}
	
	public static DataSourceInvoker getInvoker(String type) throws DataSourceInvokerNotFoundException {
		if (userInvokerHolder.containsKey(type) ) {
			return userInvokerHolder.get(type);
		}
		if (defaultInvokerHolder.containsKey(type) ) {
			return defaultInvokerHolder.get(type);
		}
		throw new DataSourceInvokerNotFoundException(" datasource ["+type+"] not supported. ");
	}
	
	private static void setDefaultInvoker(String type,DataSourceInvoker invoker) {
		defaultInvokerHolder.put(type, invoker);
	}
	
	public static void setInvoker(String type,DataSourceInvoker invoker) {
		userInvokerHolder.put(type, invoker);
	}
	
}
