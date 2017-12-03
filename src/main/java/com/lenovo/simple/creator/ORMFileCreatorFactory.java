package com.lenovo.simple.creator;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lenovo.simple.template.TemplateFacoty;
import com.lenovo.simple.template.exception.TemplateInvokerNotFoundException;

public class ORMFileCreatorFactory {

	public static void createFile(String model,String subfix,String floder,String beanName,Map<String,Object> param) {
		String type = model.substring(model.lastIndexOf(".")+1);
		try {
			TemplateFacoty.getInvoker(type).createFile(model, beanName+StringUtils.trimToEmpty(subfix),floder,param);;
		} catch (TemplateInvokerNotFoundException e) {
			e.printStackTrace();
		}
	}
}
