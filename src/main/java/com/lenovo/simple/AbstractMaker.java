package com.lenovo.simple;

import java.util.List;
import java.util.Map;

import com.lenovo.simple.configure.EntityFactory;
import com.lenovo.simple.configure.Container;
import com.lenovo.simple.configure.table.DbColumn;
import com.lenovo.simple.configure.table.Table;
import com.lenovo.simple.creator.LogicBeanCreatorFacoty;
import com.lenovo.simple.creator.ORMFileCreatorFactory;
import com.lenovo.simple.dataSource.DataSourceInvokerFactory;
import com.lenovo.simple.dataSource.exception.DataSourceInvokerNotFoundException;
import com.lenovo.simple.parse.exception.SimpleFileNotValidException;
import com.lenovo.simple.parse.exception.SimpleParseException;
import com.lenovo.simple.parse.exception.SimpleParserNotFoundException;

public abstract class AbstractMaker {

	public void make(String file) {
		//解析出实体
		List<Container> containers = loadContainers(file);
		if ( null != containers) {
			for (Container container : containers) {
				List<Table> entitys =  container.getTables();
				if ( null != entitys && entitys.size() > 0 ) {
					for (int i = 0 ; i < entitys.size() ; i ++) {
						Table e = entitys.get(i);
						//读取数据库表信息
						List<DbColumn> dbcolumns = queryTable(container,e);
						if ( null != dbcolumns && dbcolumns.size() > 0 ) {
							e.setDbcolumns(dbcolumns);
						}
						Map<String,Object> param = definedTemplateParam(container,e);
						//生成实体
						if (null != container.getBean()) {
							LogicBeanCreatorFacoty.createFile(container.getBean().getModel(), container.getBean().getLpackage(), container.getBean().getSubfix(), 
								container.getBean().getFloder(), e.getBeanName(), param);
						}
						//生成orm
						if (null != container.getOrm()) {
							ORMFileCreatorFactory.createFile(container.getOrm().getModel(), container.getOrm().getSubfix(), container.getOrm().getFloder(), e.getName(), param);
						}
						//生成dao
						if (null != container.getDao()) {
							LogicBeanCreatorFacoty.createFile(container.getDao().getModel(), container.getDao().getLpackage(), container.getDao().getSubfix(), 
								container.getDao().getFloder(), e.getBeanName(), param);
						}
						//生成Service
						if (null != container.getService()) {
							LogicBeanCreatorFacoty.createFile(container.getService().getModel(), container.getService().getLpackage(), container.getService().getSubfix(), 
								container.getService().getFloder(), e.getBeanName(), param);
						}
						//生成serviceImpl
						if (null != container.getServiceImpl()) {
							LogicBeanCreatorFacoty.createFile(container.getServiceImpl().getModel(), container.getServiceImpl().getLpackage(), container.getServiceImpl().getSubfix(), 
								container.getServiceImpl().getFloder(), e.getBeanName(), param);
						}
						//生成其他文件
						createOther(container,e);
					}
				}
			}
		}
	}
	
	protected abstract void createOther(Container container,Table table);
	
	protected abstract Map<String,Object> definedTemplateParam(Container container,Table table);
	
	private static List<Container> loadContainers(String file) {
		try {
			return EntityFactory.parseContainer(file);
		} catch (SimpleFileNotValidException e) {
			e.printStackTrace();
		} catch (SimpleParserNotFoundException e) {
			e.printStackTrace();
		} catch (SimpleParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static List<DbColumn> queryTable(Container container,Table e) {
		if ( null == container.getDataSource()) {
			return null;
		}
		try {
			return DataSourceInvokerFactory.getInvoker(container.getDataSource().getType()).queryTable(container.getDataSource(),e);
		} catch (DataSourceInvokerNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	
}
