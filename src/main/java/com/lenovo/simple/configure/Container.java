package com.lenovo.simple.configure;

import java.util.List;

import com.lenovo.simple.configure.configs.LogicBean;
import com.lenovo.simple.configure.configs.Orm;
import com.lenovo.simple.configure.table.Table;

public final class Container {

	private DataSource dataSource;
	private Orm orm;
	private LogicBean bean;
	private LogicBean dao;
	private LogicBean service;
	private LogicBean serviceImpl;
	private LogicBean controller;
	
	
	private List<Table> tables;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Orm getOrm() {
		return orm;
	}

	public void setOrm(Orm orm) {
		this.orm = orm;
	}

	public LogicBean getBean() {
		return bean;
	}

	public void setBean(LogicBean bean) {
		this.bean = bean;
	}

	public LogicBean getDao() {
		return dao;
	}

	public void setDao(LogicBean dao) {
		this.dao = dao;
	}

	public LogicBean getService() {
		return service;
	}

	public void setService(LogicBean service) {
		this.service = service;
	}

	public LogicBean getServiceImpl() {
		return serviceImpl;
	}

	public void setServiceImpl(LogicBean serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	public LogicBean getController() {
		return controller;
	}

	public void setController(LogicBean controller) {
		this.controller = controller;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
}
