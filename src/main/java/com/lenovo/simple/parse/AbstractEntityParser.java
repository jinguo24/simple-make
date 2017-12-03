package com.lenovo.simple.parse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lenovo.simple.configure.Container;
import com.lenovo.simple.configure.DataSource;
import com.lenovo.simple.configure.configs.LogicBean;
import com.lenovo.simple.configure.configs.Orm;
import com.lenovo.simple.configure.table.Table;


public abstract class AbstractEntityParser<T> implements EntityParser{

	private static final Log log = LogFactory.getLog(AbstractEntityParser.class);
	
	@Override
	public List<Container> parseContainer(String file) {
		try {
			T root = getResource(file);
			if ( null == root ) {
				log.warn(this.getClass().getName()+ " getResource error , root is null. ");
				return null;
			}
			List containers = getContainers(root); 
			if (null == containers || containers.size() ==0 ) {
				log.warn(this.getClass().getName()+ " containers is null. ");
				return null;
			}
			List<Container> clist = new ArrayList<Container>();
			for (Iterator it = containers.iterator(); it.hasNext();) {  
				Container mc = new Container();
				T e = (T) it.next();
				mc.setDataSource(getDataSource(e));
				mc.setOrm(getOrm(e));
				mc.setBean(getBean(e));
				mc.setDao(this.getDao(e));
				mc.setService(this.getService(e));
				mc.setServiceImpl(this.getServiceImpl(e));
				mc.setController(this.getController(e));
				mc.setTables(this.getTables(e));
				clist.add(mc);
			}
			if (clist.size() > 0 ) {
				return clist;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected abstract T getResource(String file); 
	
	protected abstract List<T> getContainers(T root);
	
	protected abstract DataSource getDataSource(T container);
	
	protected abstract Orm getOrm(T container) ;
	
	protected abstract LogicBean getBean(T container);
	
	protected abstract LogicBean getDao(T container);
	
	protected abstract LogicBean getService(T container);
	
	protected abstract LogicBean getServiceImpl(T container);
	
	protected abstract LogicBean getController(T container);
	
	protected abstract List<Table> getTables(T container);
}
