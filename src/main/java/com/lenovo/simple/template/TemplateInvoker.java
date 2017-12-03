package com.lenovo.simple.template;

import java.util.Map;

public interface TemplateInvoker {
	public void createFile(String template,String fileName,String floder,Map<String,Object> param);
}
