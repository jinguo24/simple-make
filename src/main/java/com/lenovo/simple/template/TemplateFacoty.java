package com.lenovo.simple.template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lenovo.simple.template.exception.TemplateInvokerNotFoundException;
import com.lenovo.simple.template.support.freeMarker.FreeMarkerInvoker;

public class TemplateFacoty {

	private static final Map<String,TemplateInvoker> defaultTemplateHolder = new ConcurrentHashMap<String,TemplateInvoker>();
	private static final Map<String,TemplateInvoker> userTemplateHolder = new ConcurrentHashMap<String,TemplateInvoker>();
	
	static {
		setDefaultInvoker("ftl",new FreeMarkerInvoker());
	}
	
	public static TemplateInvoker getInvoker(String type) throws TemplateInvokerNotFoundException {
		if (userTemplateHolder.containsKey(type) ) {
			return userTemplateHolder.get(type);
		}
		if (defaultTemplateHolder.containsKey(type) ) {
			return defaultTemplateHolder.get(type);
		}
		 throw new TemplateInvokerNotFoundException("TemplateInvoker not support type :"+type);
	}
	
	private static void setDefaultInvoker(String type, TemplateInvoker invoker) {
		defaultTemplateHolder.put(type, invoker);
	}
	
	public static void setInvoker(String type, TemplateInvoker invoker) {
		userTemplateHolder.put(type, invoker);
	}
}
