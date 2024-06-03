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
import com.dao.SeatDao;
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

public class SeatManage extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public SeatManage() {
		setLayout(null);
		this.setBounds(10, 10,899, 499);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "厅位管理", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 153)));
		panel.setBounds(10, 10, 866, 144);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("厅位号");
		lblNewLabel.setBounds(5, 24, 59, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(74, 21, 216, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("厅位人数");
		lblNewLabel_1.setBounds(5, 51, 59, 15);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(74, 48, 216, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("厅位号（操作）");
		lblNewLabel_6.setBounds(456, 24, 139, 15);
		panel.add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setBounds(579, 21, 155, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("增加厅位");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//增加菜品
				String name=textField.getText();
				String peo=textField_1.getText();
			
				if(name.equals("")) {
					Tools.messageWindows("请输入厅位号");
				}else if(peo.equals("")) {
					Tools.messageWindows("请输入厅位人数");
				}else {
					
					
			        	int a=new SeatDao().saveSeat(name,peo);
			        	if(a==0||a==-1) {
			        		Tools.messageWindows("添加失败，厅位号重复或者人数格式错误");
			        	}else {
			        		Tools.messageWindows("添加成功");
			        	}
			        	
			        
			    
				
				
				
				
				
				
					
				}
			}
		});
		btnNewButton_1.setBounds(456, 51, 97, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("删除厅位");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name=textField_4.getText();
				if(name.equals("")) {
					Tools.messageWindows("请输入厅位号(操作)");
				}else {
					int a=new SeatDao().delSeat(name);
		
		        	if(a==0||a==-1) {
		        		Tools.messageWindows("删除失败，厅位号不存在");
		        	}else {
		        		Tools.messageWindows("删除成功");
		        	}
				}
				
			
				
			}
		});
		btnNewButton_1_1.setBounds(456, 100, 97, 23);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("更改厅位");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//增加菜品
				String name=textField.getText();
				String peo=textField_1.getText();
				
				String name1=textField_4.getText();
			
				if(name.equals("")) {
					Tools.messageWindows("请输入厅位号");
				}else if(peo.equals("")) {
					Tools.messageWindows("请输入厅位人数");
				}else 	if(name.equals("")) {
					Tools.messageWindows("请输入厅位(操作)"); 
				}else {
						
					int a=new SeatDao().upSeat(name,peo,name1);
		        	if(a==0||a==-1) {
		        		Tools.messageWindows("更改失败，厅位号不存在或重名,或者厅位正在进餐");
		        	}else {
		        		Tools.messageWindows("更改成功");
		        	}
		        	
			        
			        
					
					
					
					
				}
					
			
			    
				
				
				
			}
		});
		btnNewButton_1_2.setBounds(563, 51, 97, 23);
		panel.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("查找厅位");
		
		btnNewButton_1_3.setBounds(563, 97, 97, 23);
		panel.add(btnNewButton_1_3);
		
		
		
		
		
		
		//________________________________________________________
		
		Object columns[] ={"厅位号","厅位人数","当前人数","状态"};//创建表格	
		Table t1Table=new Table(columns);
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(800,280));//设置整个滚动条窗口的大小
		JS.setBounds(10, 164, 866, 325);
		add(JS);
		
		
		
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField_4.getText();
				if(name.equals("")) {
					//查找全部
					
					ResultSet rs = new SeatDao().showSeat();
					int count = Tools.addDataTable(rs, model, 4);//向表格里面添加数据
					
					
					
				}else {
					//查找单个
					ResultSet rs = new SeatDao().showSeat(name);
					int count = Tools.addDataTable(rs, model, 4);//向表格里面添加数据
					ResultSet rs2 = new SeatDao().showSeat(name);
					try {
						if(rs2.next()) {
							
							textField.setText(rs2.getString(1));
							textField_1.setText(rs2.getString(2));
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//
				}
				
			}
		});
	
	
		


	}
}
