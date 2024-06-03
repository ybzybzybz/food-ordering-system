package com.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBUtilZ {

	public static Connection con=null;
	
	/**
	 * 
	 * @param account 账号
	 * @param password  密码
	 * @param databasName 数据库的名字
	 */
	public DBUtilZ(String account,String password,String databasName) {
		
		//加载驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("驱动加载失败");
			e.printStackTrace();
			
		}//加载8.0的驱动  
		
		
		String url="jdbc:mysql://localhost:3306/"+databasName+"?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
		//连接数据库
		try {
			con=DriverManager.getConnection(url,account, password);//url  account password
			//DBUtil.con=DBUtilZ.con;
			
			System.out.println("连接数据库成功");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("链接数据库失败");
			String temp=e.getMessage();
			System.out.println(temp);
			String[] arr1=temp.split(" ");
			if(arr1[0].equals("Unknown")) {
				System.out.println("请建立名字为："+arr1[2]+"数据库");
			}
			if(arr1[0].equals("Access")) {
				System.out.println("请检查数据库密码是否正确：数据库密码错误");
			}
			if(temp.contains("the server was 0 milliseconds ago")){
				System.out.println("请安装Mysql数据库");
			}
}
			
			
			
			
			
			
			//
		
		
	
		
		
	}
	
	
}

