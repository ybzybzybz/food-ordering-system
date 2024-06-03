package com.dao.impl;

import java.sql.ResultSet;

public interface SeatDaoImpl {
	
	
	
	//厅位号，一个人数  
	public int saveSeat(String id,String peo);
	
	
	public int delSeat(String id);
	
	public int upSeat(String ...data);
	
	public ResultSet  showSeat(String ...data);
	public ResultSet  showSeat();

}
