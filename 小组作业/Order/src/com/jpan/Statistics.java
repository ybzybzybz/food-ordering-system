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

public class Statistics extends JPanel {

	/**
	 * Create the panel.
	 */
	public Statistics() {
		setLayout(null);
		this.setBounds(10, 10,899, 499);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "统计管理", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 153)));
		panel.setBounds(10, 10, 866, 144);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("查询厅位状态");
		
		btnNewButton_1.setBounds(21, 27, 229, 23);
		panel.add(btnNewButton_1);
		
		
		
		
		
		
		//________________________________________________________
		
		Object columns[] ={"厅位","最大人数","当前人数","状态","未上桌菜品","已上桌菜品"};//创建表格	
		Table t1Table=new Table(columns);
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(800,280));//设置整个滚动条窗口的大小
		JS.setBounds(10, 164, 866, 325);
		add(JS);
	
	
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//增加菜品
			
			    ResultSet rs = Mysqld.QueryData("select d_id,d_mpeo,d_peo,if(d_sta='0','空闲','占用') d_sta,d_foodid from s_seat", null);
				
				try {
					model.setRowCount(0);
					while(rs.next()) {
						
						String data[]=new String[6];
						data[0]=rs.getString("d_id");
						data[1]=rs.getString("d_mpeo");
						data[2]=rs.getString("d_peo");
						data[3]=rs.getString("d_sta");
						data[4]="0";
						data[5]="0";
						String foodid=rs.getString("d_foodid");
						String da[]= { foodid};
						ResultSet rs2 = Mysqld.QueryData("select count(*) from s_food where d_foodid=? and d_sta='0'", da);
						if(rs2.next()) {
							data[4]=rs2.getString(1);//没有上的菜
						}
						rs2.close();
						rs2 = Mysqld.QueryData("select count(*) from s_food where d_foodid=? and d_sta='1'", da);
						if(rs2.next()) {
							data[5]=rs2.getString(1);//没有上的菜
						}
						rs2.close();
					
						model.addRow(data);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
					
				
			}
		});


	}
}
