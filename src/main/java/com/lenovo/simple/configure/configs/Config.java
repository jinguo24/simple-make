package com.lenovo.simple.configure.configs;

public class Config {
	protected String model;
	protected String floder;
	protected String subfix;
	protected String basePackage;
	public String getSubfix() {
		return subfix;
	}
	public void setSubfix(String subfix) {
		this.subfix = subfix;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFloder() {
		return floder;
	}
	public void setFloder(String floder) {
		this.floder = floder;
	}
	public String getBasePackage() {
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
}
