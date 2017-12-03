package com.lenovo.simple.creator;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lenovo.simple.template.TemplateFacoty;
import com.lenovo.simple.template.exception.TemplateInvokerNotFoundException;

public class PageCreatorFactory {

	public static void createFile(String model,String floder,String subfix,String name,Map<String,Object> param) {
		String type = model.substring(model.lastIndexOf(".")+1);
		try {
			TemplateFacoty.getInvoker(type).createFile(model, name+StringUtils.trimToEmpty(subfix),floder,param);;
		} catch (TemplateInvokerNotFoundException e) {
			e.printStackTrace();
		}
	}
}
