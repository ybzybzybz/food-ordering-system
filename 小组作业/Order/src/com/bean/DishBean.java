package com.bean;

public class DishBean {
	
	private String dname;
	private String dkouwei;
	private String djiage;
	private String d_jianjie;
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDkouwei() {
		return dkouwei;
	}
	public void setDkouwei(String dkouwei) {
		this.dkouwei = dkouwei;
	}
	public String getDjiage() {
		return djiage;
	}
	public void setDjiage(String djiage) {
		this.djiage = djiage;
	}
	public String getD_jianjie() {
		return d_jianjie;
	}
	public void setD_jianjie(String d_jianjie) {
		this.d_jianjie = d_jianjie;
	}
	public DishBean(String dname, String dkouwei, String djiage, String d_jianjie) {
		super();
		this.dname = dname;
		this.dkouwei = dkouwei;
		this.djiage = djiage;
		this.d_jianjie = d_jianjie;
	}
	public DishBean() {
		super();
	}

}
