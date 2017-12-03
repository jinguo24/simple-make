package com.lenovo.simple.parse.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lenovo.simple.configure.DataSource;
import com.lenovo.simple.configure.configs.LogicBean;
import com.lenovo.simple.configure.configs.Orm;
import com.lenovo.simple.configure.table.Table;
import com.lenovo.simple.configure.table.TableColumn;
import com.lenovo.simple.parse.AbstractEntityParser;

public final class DefaultXmlParser extends AbstractEntityParser<Element> {

	private static final Log log = LogFactory.getLog(DefaultXmlParser.class);
	
	@Override
	protected Element getResource(String file) {
		try {
			SAXReader reader = new SAXReader();         
			Document document = reader.read(new File(file));  
			return document.getRootElement();  
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error(" DefaultXmlParser getResource error :",e);
		}
		return null;
	}
	
	@Override
	protected DataSource getDataSource(Element container) {
		Element e = container.element("datasource");
		if ( null == e) {
			return null;
		}
		DataSource source = new DataSource();
		Element url = e.element("url");
		if ( null == url ) {
			log.error(" DefaultXmlParser datasource has no url node. ");
			return null;
		}
		Element username = e.element("username");
		if ( null == username ) {
			log.error(" DefaultXmlParser datasource has no username node. ");
			return null;
		}
		Element password = e.element("password");
		if ( null == password ) {
			log.error(" DefaultXmlParser datasource has no password node. ");
			return null;
		}
		Element type = e.element("type");
		if ( null == type ) {
			log.error(" DefaultXmlParser datasource has no type node. ");
			return null;
		}
		
		if ( null != username.attributeValue("value")) {
			source.setName(username.attributeValue("value").toString());
		}else {
			source.setName(username.getTextTrim());
		}
		
		if( null != password.attributeValue("value") ) {
			source.setPassword(password.attributeValue("value").toString());
		}else {
			source.setPassword(password.getTextTrim());
		}
		
		if ( null != url.attributeValue("value") ) {
			source.setUrl(url.attributeValue("value").toString());
		}else {
			source.setUrl(url.getTextTrim());
		}
		
		if ( null != type.attributeValue("value") ) {
			source.setType(type.attributeValue("value").toString());
		}else {
			source.setType(type.getTextTrim());
		}
		
		return source;
	}

	@Override
	protected Orm getOrm(Element container) {
		Element config = container.element("config");
		if ( null == config) {
			return null;
		}
		
		Element e = config.element("orm");
		if ( null == e ) {
			return null; 
		}
		
		Orm orm = new Orm();
		orm.setModel(config.attributeValue("modelfloder")+"/"+e.attributeValue("model"));
		orm.setFloder(config.attributeValue("filefloder")+"/"+e.attributeValue("floder"));
		orm.setSubfix(StringUtils.trimToEmpty(e.attributeValue("subfix")));
		orm.setType(e.attributeValue("type"));
		return orm;
	}

	private LogicBean getLogicBean(Element container,String name) {
		Element config = container.element("config");
		if ( null == config ) {
			return null; 
		}
		Element bean = config.element(name);
		if ( null == bean ) {
			return null; 
		}
		LogicBean lbean = new LogicBean();
		lbean.setLpackage(bean.attributeValue("package"));
		lbean.setModel(config.attributeValue("modelfloder")+"/"+bean.attributeValue("model"));
		lbean.setFloder(config.attributeValue("filefloder")+"/"+bean.attributeValue("floder"));
		lbean.setSubfix(bean.attributeValue("subfix"));
		return lbean;
	}
	
	@Override
	protected LogicBean getBean(Element container) {
		return getLogicBean(container,"bean");
	}

	@Override
	protected LogicBean getDao(Element container) {
		return getLogicBean(container,"dao");
	}

	@Override
	protected LogicBean getService(Element container) {
		return getLogicBean(container,"service");
	}

	@Override
	protected LogicBean getServiceImpl(Element container) {
		return getLogicBean(container,"serviceImpl");
	}

	@Override
	protected LogicBean getController(Element container) {
		return getLogicBean(container,"controller");
	}


	@Override
	protected List<Table> getTables(Element container) {
		Element entites = container.element("tables");
		if ( null == entites ) {
			return null; 
		}
		List<Element> entitylist = entites.elements("table");
		if ( null != entitylist && entitylist.size() > 0) {
			List<Table> entityModelList = new ArrayList<Table>();
			for (Element entity : entitylist) {
				Table table = new Table();
				table.setName(entity.attributeValue("name"));
				List<Element> columns = entity.elements("column");
				if ( null != columns && columns.size() > 0 ) {
					Map<String,TableColumn> alist = new HashMap<String,TableColumn>();
					for (Element ea:columns) {
						TableColumn tc = new TableColumn();
						tc.setName(ea.attributeValue("name"));
						tc.setCombor(ea.attributeValue("combor"));
						alist.put(tc.getName(), tc);
					}
					table.setColumns(alist);
				}
				entityModelList.add(table);
			}
			return entityModelList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Element> getContainers(Element root) {
		List<Element> roots = root.elements("container");
		if ( null == roots || roots.size() == 0 ) {
			log.error(" DefaultXmlParser has no container node.");
			return null; 
		}
		return roots;
	}

	

	

	
}
