package com.lenovo.simple.template.support.freeMarker;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import com.lenovo.simple.template.TemplateInvoker;
import freemarker.template.TemplateException;

public class FreeMarkerInvoker implements TemplateInvoker {

	@Override
	public void createFile(String template, String fileName, String floder,Map<String,Object> param) {
		try {
			new FreeMarkerUtils(template).makeFileByFile(template, floder+File.separator+fileName, param, false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}
}
