package com.lenovo.simple.template.support.freeMarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtils {

	private static final Log log = LogFactory.getLog(FreeMarkerUtils.class);
	private Configuration config = new freemarker.template.Configuration();

	public FreeMarkerUtils(String modelPath) {
		File file = new File(modelPath);
		try {
			config.setDirectoryForTemplateLoading(file.getParentFile());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		config.setObjectWrapper(new DefaultObjectWrapper());  
		config.setDefaultEncoding("utf-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
	}
	
	public void makeFileByFile(String templateFilePath, String destFilePath,
			Map<String, Object> model) throws IOException, TemplateException {
		File file = new File(templateFilePath);
		Template t = config.getTemplate(file.getName());
		File destFile = new File(destFilePath);
		if (destFile.exists()) {
			destFile.delete();
		}
		File parent = destFile.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile, false), "utf-8"));
		t.process(model, out);
		out.close();
		log.info(" create file success."+destFilePath);
	}

	public String makeStringByString(String templateContent, Map<String, Object> model)
			throws IOException, TemplateException {
		Template t = new Template(String.valueOf(templateContent.hashCode()), templateContent, config);
		java.io.StringWriter writer = new java.io.StringWriter();
		t.process(null, writer);
		return writer.toString();
	}
}
