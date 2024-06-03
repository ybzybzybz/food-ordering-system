package com.dao;

import java.sql.ResultSet;

import com.dao.impl.DishDaoImpl;
import com.dao.impl.SeatDaoImpl;
import com.mysqld.Mysqld;

public class SeatDao implements   SeatDaoImpl{

	@Override
	public int saveSeat(String id, String peo) {
		// TODO Auto-generated method stub
		
		String data[]= {id,peo};
		int a = Mysqld.upDate("insert into s_seat (d_id,d_mpeo) VALUES (?,?)", data);
		return a;
		
	}

	@Override
	public int delSeat(String id) {
		// TODO Auto-generated method stub
		String data[]= {id};

		int a = Mysqld.upDate("DELETE FROM s_seat where d_id=?", data);
		
		return a;
	}

	@Override
	public int upSeat(String... data) {
		// TODO Auto-generated method stub
		int a = Mysqld.upDate("update s_seat set d_id=?,d_mpeo=? where d_id=? and d_sta='0'", data);
		return a;
	}

	@Override
	public ResultSet  showSeat(String... data) {
		// TODO Auto-generated method stub
		ResultSet rs = Mysqld.QueryData("select * from s_seat where d_id=?", data);
		return rs;
	}

	@Override
	public ResultSet  showSeat() {
		// TODO Auto-generated method stub
		ResultSet rs = Mysqld.QueryData("select * from s_seat ", null);
		return rs;
	}

}
