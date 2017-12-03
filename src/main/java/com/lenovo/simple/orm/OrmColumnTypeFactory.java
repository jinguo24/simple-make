package com.lenovo.simple.orm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lenovo.simple.orm.exception.OrmColumnTypeServiceNotFoundException;
import com.lenovo.simple.orm.support.MybatisColumnTypeServiceImpl;

public class OrmColumnTypeFactory {

	private static final Map<String,OrmColumnTypeService> defaultTemplateHolder = new ConcurrentHashMap<String,OrmColumnTypeService>();
	private static final Map<String,OrmColumnTypeService> userTemplateHolder = new ConcurrentHashMap<String,OrmColumnTypeService>();
	
	static {
		setDefaultInvoker("mybatis",new MybatisColumnTypeServiceImpl());
	}
	
	public static OrmColumnTypeService getInvoker(String type) throws OrmColumnTypeServiceNotFoundException {
		if (userTemplateHolder.containsKey(type) ) {
			return userTemplateHolder.get(type);
		}
		if (defaultTemplateHolder.containsKey(type) ) {
			return defaultTemplateHolder.get(type);
		}
		 throw new OrmColumnTypeServiceNotFoundException("TemplateInvoker not support type :"+type);
	}
	
	private static void setDefaultInvoker(String type, OrmColumnTypeService invoker) {
		defaultTemplateHolder.put(type, invoker);
	}
	
	public static void setInvoker(String type, OrmColumnTypeService invoker) {
		userTemplateHolder.put(type, invoker);
	}
}
