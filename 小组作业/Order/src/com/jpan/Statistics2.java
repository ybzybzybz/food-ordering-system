package com.jpan;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.bean.DishBean;
import com.dao.DishDao;
import com.mysqld.Mysqld;
import com.tools.Table;
import com.tools.Tools;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;

public class Statistics2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public Statistics2() {
		setLayout(null);
		this.setBounds(10, 10,899, 499);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "统计管理", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 153)));
		panel.setBounds(10, 10, 866, 144);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("查询近一周销量统计");
		
		btnNewButton_1.setBounds(21, 27, 229, 23);
		panel.add(btnNewButton_1);
		
		
		
		
		
		
		//________________________________________________________
		
		Object columns[] ={"菜品名字","销量","销售额"};//创建表格	
		Table t1Table=new Table(columns);
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(800,280));//设置整个滚动条窗口的大小
		JS.setBounds(10, 164, 866, 325);
		add(JS);
	
	
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//增加菜品
			
			    ResultSet rs = Mysqld.QueryData("select s_food.d_name,sum(s_food.d_count),sum(s_food.d_count*s_dish.d_jiage) from s_food  LEFT JOIN s_dish on s_food.d_name=s_dish.d_name   where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(s_food.d_time) GROUP BY s_food.d_name", null);
				
				Tools.addDataTable(rs, model, 3);
				ResultSet rs2 = Mysqld.QueryData("select s_food.d_name,sum(s_food.d_count),sum(s_food.d_count*s_dish.d_jiage) from s_food  LEFT JOIN s_dish on s_food.d_name=s_dish.d_name   where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(s_food.d_time) GROUP BY s_food.d_name", null);
					
				try {
					int a=0;//总销售数量
					int b=0;//总销售额
					while(rs2.next()) {
						a=a+rs2.getInt(2);
						b=b+rs2.getInt(3);
						
					}
					rs2.close();
					String data[]= {"统计",String.valueOf(a)+"份",String.valueOf(b)+"元"};
					model.addRow(data);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
					
				
			}
		});


	}
}
