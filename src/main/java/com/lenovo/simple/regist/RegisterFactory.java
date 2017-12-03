package com.lenovo.simple.regist;

import com.lenovo.simple.dataSource.DataSourceInvoker;
import com.lenovo.simple.dataSource.DataSourceInvokerFactory;
import com.lenovo.simple.orm.OrmColumnTypeFactory;
import com.lenovo.simple.orm.OrmColumnTypeService;
import com.lenovo.simple.parse.EntityParser;
import com.lenovo.simple.parse.EntityParserRegister;
import com.lenovo.simple.template.TemplateFacoty;
import com.lenovo.simple.template.TemplateInvoker;
import com.lenovo.simple.template.exception.TemplateInvokerNotFoundException;

public class RegisterFactory {

	public static void registParser(String fileType,EntityParser parser) {
		EntityParserRegister.setParser(fileType, parser);
	}
	
	public static void registTemplateInvoker(String type,TemplateInvoker invoker) {
		TemplateFacoty.setInvoker(type, invoker);
	}
	
	public static TemplateInvoker getTemplateInvoker(String type) {
		try {
			return TemplateFacoty.getInvoker(type);
		} catch (TemplateInvokerNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setDataSourceInvoker(String type,DataSourceInvoker invoker) {
		DataSourceInvokerFactory.setInvoker(type, invoker);
	}
	
	public static void setOrmColumnTypeService(String type,OrmColumnTypeService service) {
		OrmColumnTypeFactory.setInvoker(type, service);
	}
	
}
