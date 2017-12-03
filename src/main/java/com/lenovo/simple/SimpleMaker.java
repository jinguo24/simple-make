package com.lenovo.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lenovo.simple.configure.Container;
import com.lenovo.simple.configure.table.DbColumn;
import com.lenovo.simple.configure.table.Table;
import com.lenovo.simple.dataSource.IDStragegy;
import com.lenovo.simple.orm.exception.OrmColumnTypeServiceNotFoundException;
import com.lenovo.simple.parse.EntityParserRegister;
import com.lenovo.simple.util.ParamUtil;

public class SimpleMaker extends AbstractMaker {

	@Override
	protected void createOther(Container container, Table entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Map<String, Object> definedTemplateParam(Container container,
			Table table) {
		 Map<String, Object> param = new  HashMap<String,Object>();
		 param.put("beanpackage", container.getBean().getLpackage());
		 param.put("beanName", table.getBeanName());
		 //bean
		 param.put("beanNameLowerCase", table.getName());
		 param.put("beanId", container.getBean().getLpackage().replace(".", "_")+"_"+table.getBeanName().toLowerCase());
		 param.put("beanContentlist", ParamUtil.getJavaPojoBeanContents(container.getDataSource().getType(), table));
		 //orm
		 try {
			param.put("ormFields", ParamUtil.getOrmFields(container, table, "mybatis"));
		} catch (OrmColumnTypeServiceNotFoundException e) {
			e.printStackTrace();
		}
		 param.put("dbfields", getAllFields(table));
		 param.put("tableName", table.getName());
		 //dao
		 param.put("datatemplate", "sqlSessionTemplate_btc");
		 param.put("daoPackage", container.getDao().getLpackage());
		 param.put("daoSubfix", container.getDao().getSubfix());
		 //service
		 param.put("servicePackage", container.getService().getLpackage());
		 param.put("serviceSubfix", container.getService().getSubfix());
		 //serviceImpl
//		 param.put("serviceImplPackage", container.getServiceImpl().getLpackage());
//		 param.put("serviceImplSubfix", container.getServiceImpl().getSubfix());
		 
		 
//		 param.put("pkAssigned", IDStragegy.identity.getType().equals(entity.getPk().getStrategy())? false : true);
//		 param.put("pkSetMethod", ParamUtil.getSetMethodName(entity.getPk().getField()));
//		 param.put("attributes", entity.getAttributes());
//		 
//		 param.put("dbfieldsWithNoPk", StringUtils.join(getFields(entity,false),","));
//		 
//		 param.put("queryFields", getQueryFields(entity));
//		 
//		 
//		 
//		 param.put("serviceId", container.getService().getLpackage().replace(".", "_")+"_"+entity.getBeanName().toLowerCase()+container.getService().getSubfix());
//
//		 param.put("controllerPackage", container.getController().getLpackage());
//		 param.put("controllerSubfix", container.getController().getSubfix());
		 return param;
	}
	
	private String getAllFields(Table table) {
		StringBuffer sb = new StringBuffer();
		for (DbColumn dc: table.getDbcolumns()) {
			sb.append(dc.getColumnName()).append(",");
		}
		return sb.toString().substring(0,sb.length()-1);
	}
//
//	private List<Attribute> getQueryFields(Entity entity) {
//		List<Attribute> fields = new ArrayList<Attribute>();
//		for (Attribute attr: entity.getAttributes()) {
//			if (attr.isQuery()) {
//				fields.add(attr);
//			}
//		}
//		return fields;
//	}
	
	
	public static void main(String[] args) {
		SimpleMaker s = new SimpleMaker();
		s.make("F:/Code/codegenerator/simple-make/src/main/resources/define.xml");
	}
}
