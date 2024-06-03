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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.bean.DishBean;
import com.dao.DishDao;
import com.dao.SeatDao;
import com.mysqld.Mysqld;
import com.tools.Table;
import com.tools.Tools;
import com.view.LoginWindows;



import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UserDianOrder extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	String user="admin";
	public UserDianOrder() {
		setLayout(null);
		this.setBounds(10, 10,899, 499);
		user=LoginWindows.textField.getText();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u83DC\u54C1\u7BA1\u7406", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 153)));
		panel.setBounds(10, 10, 866, 62);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("厅位");
		lblNewLabel.setBounds(5, 24, 59, 15);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1_3 = new JButton("查找点餐");
		
		
		btnNewButton_1_3.setBounds(370, 20, 97, 23);
		panel.add(btnNewButton_1_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"请选择厅位"}));
		comboBox.setBounds(74, 20, 97, 23);
		panel.add(comboBox);
		
		
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("就餐人数");
		lblNewLabel_2.setBounds(191, 24, 80, 15);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(254, 21, 106, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("完成点餐");
		
		btnNewButton.setBounds(488, 20, 97, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("结账");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedIndex()==0) {
					Tools.messageWindows("请选择厅位");
				}else {
					String ting=String.valueOf(comboBox.getSelectedItem());
					String data[]= {ting,user};
					ResultSet rs = Mysqld.QueryData("select * from s_seat where d_id=? and d_user=?", data);
					try {
						if(rs.next()) {
							//代表是我的厅位
							String da[]= {rs.getString("d_foodid")};
							ResultSet rs1 = Mysqld.QueryData("select s_food.d_name,s_dish.d_jiage,s_food.d_count,if(s_food.d_sta=0,'未上菜','已上菜') a from s_food\r\n"
									+ "left JOIN s_dish on s_food.d_name=s_dish.d_name where s_food.d_foodid=?", da);
							//将这个所有东西都查询到
							int c=0;
							while(rs1.next()) {
								String a=rs1.getString("a");
								if(a.equals("未上菜")) {
									Tools.messageWindows("有菜品未上菜不能结账");
									c=1;
									break;
								}
							}
							rs1.close();
							if(c==0) {
								//就是代表全部豆上菜了
								ResultSet rs2 = Mysqld.QueryData("select sum(s_food.d_count*s_dish.d_jiage) a from s_food\r\n"
										+ "left JOIN s_dish on s_food.d_name=s_dish.d_name where s_food.d_foodid=?", da);
								if(rs2.next()) {
									String dac[]= {ting};
									Mysqld.upDate("update s_seat set d_sta='0' where d_id=?",dac);
									Tools.messageWindows("一共消费"+rs2.getString("a")+"元，支付成功");
								}else {
									Tools.messageWindows("支付失败");
								}
								
							}
							
							
						}else {
							Tools.messageWindows("您未在当前厅位点餐");
						}
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton_1.setBounds(609, 20, 97, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("刷新厅位");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.removeAllItems();
				comboBox.addItem("请选择厅位");
				ResultSet rs = new SeatDao().showSeat();
				//第一次查询所有的 然后再过滤
				try {
					while(rs.next()) {
						String res=rs.getString("d_sta");
						String res1=rs.getString("d_user");
						if(res.equals("0")) {
							comboBox.addItem(rs.getString("d_id"));
						}else if(res.equals("1")&&res1.equals(user)) {
							
							comboBox.addItem(rs.getString("d_id"));
						}
							
					}
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(716, 20, 97, 23);
		panel.add(btnNewButton_2);
		//程序第一次加载的时候  加载厅位  只是加载空闲的厅位
		ResultSet rs = new SeatDao().showSeat();
		//第一次查询所有的 然后再过滤
		try {
			while(rs.next()) {
				String res=rs.getString("d_sta");
				String res1=rs.getString("d_user");
				if(res.equals("0")) {
					comboBox.addItem(rs.getString("d_id"));
				}else if(res.equals("1")&&res1.equals(user)) {
					
					comboBox.addItem(rs.getString("d_id"));
				}
					
			}
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		JLabel lblNewLabel_11 = new JLabel("菜单（鼠标点击菜品选菜）");
		lblNewLabel_11.setForeground(Color.RED);
		lblNewLabel_11.setBounds(10, 75, 300, 15);
		add(lblNewLabel_11);
		
		Object columns1[] ={"菜品名字","菜品口味","菜品价格","菜品简介"};//创建表格	
		Table t1Table1=new Table(columns1);
		JScrollPane JS1 = t1Table1.getJScrollPane();
		DefaultTableModel model1 = t1Table1.getModel();
		JS1.setPreferredSize(new Dimension(800,280));//设置整个滚动条窗口的大小
		JS1.setBounds(10, 92, 866, 190);
		add(JS1);
		
		
		JLabel lblNewLabel_1 = new JLabel("已选菜品（鼠标点击撤销选菜）");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(10, 300, 300, 15);
		add(lblNewLabel_1);

		//加载点餐数据
		Tools.addDataTable(new DishDao().showDish(), model1, 4);
		
		
		//________________________________________________________
		
		Object columns[] ={"菜品名字","菜品价格","菜品数量","菜品状态"};//创建表格	
		Table t1Table=new Table(columns);
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(800,280));//设置整个滚动条窗口的大小
		JS.setBounds(10, 320, 866, 150);
		add(JS);
		//添加点餐的监听  选中进行点餐
		t1Table.getTables().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable table =(JTable) e.getSource();
				int r= table.getSelectedRow();
				if(r!=-1){//代表点击选择中的表格
					String count=String.valueOf(table.getValueAt(r,2));
					int num=Integer.parseInt(count);
					if(num>1){
						num--;
						table.setValueAt(num,r,2);
					}else{
						model.removeRow(r);
					}
					//
				}





			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		
		//添加点餐的监听  选中进行点餐
		t1Table1.getTables().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable table =(JTable) e.getSource();
				//要判断当前这个厅是否结账
				new SeatDao().showSeat(null);
				//获取厅位
				if(comboBox.getSelectedIndex()==0) {
					Tools.messageWindows("请选择厅位");
				}else {
					
					
					 int r= table.getSelectedRow();
		             // int c= table.getSelectedColumn();
		              //得到选中的单元格的值，表格中都是字符串
		              String cainame= table.getValueAt(r, 0).toString();//菜的名字
		              String jiage= table.getValueAt(r, 2).toString();//菜的名字
		              //获取已经点菜表
		              String data[]=new String[4];//存储的数组
		              data[0]=cainame;
		              data[1]=jiage;
		              
		              //循环遍历第二个表
		              int count=0;//已经点重复的数量
		              for(int i=0;i<t1Table.getTables().getRowCount();i++) {
		            	  if(t1Table.getTables().getValueAt(i, 0).equals(cainame)) {
		            		  count++;
		            		   String c = String.valueOf( t1Table.getTables().getValueAt(i, 2));
		            		  int d = Integer.parseInt(c)+1;
		            		  t1Table.getTables().setValueAt(d, i, 2);
		            	  }
		              }
		              if(count==0) {
		            	  data[2]=String.valueOf(1);
		            	  data[3]="未上餐";
		            	  model.addRow(data);
		              }
		              
		            
		              
				}
				
				
				
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//判断厅位
				if(comboBox.getSelectedIndex()==0) {
					Tools.messageWindows("请选择厅位");
				}else {
					
					//选择完厅位还需判断厅位是否被占用
					String data[]= {String.valueOf(comboBox.getSelectedItem())};
					ResultSet rs = Mysqld.QueryData("select * from s_seat where d_id=? and d_sta=0", data);
					int a=1;//1 代表的是占用
					try {
						if(rs.next()) {
							a=0;
							String peo = textField.getText();//当前就餐人数
							
							//转换成人数
							int peos=Integer.parseInt(rs.getString("d_mpeo"));
							int peoss=0;
							try {
								peoss=Integer.parseInt(peo);
							}catch(Exception E) {
								E.printStackTrace();
								Tools.messageWindows("请输入人数");
								a=2;
							}
						
							if(peoss>peos) {
								a=2;
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					if(a==0) {
						//能进行点餐
						
						
					
						
						long timeNew = System.currentTimeMillis()/ 1000; // 10位数的时间戳
						
						if(textField.getText().equals("")) {
							Tools.messageWindows("请输入就餐人数");
						}else if(model.getRowCount()==0){
							Tools.messageWindows("您还没有点餐");
						}else {
							
							String da[]= {textField.getText(),"1",String.valueOf(timeNew),user,String.valueOf(comboBox.getSelectedItem())};
							Mysqld.upDate("update s_seat set d_peo=?,d_sta=?,d_foodid=?,d_user=? where d_id=?",da);
								
							for(int i=0;i<model.getRowCount();i++) {
								String cai=String.valueOf(model.getValueAt(i, 0)) ;
						
			
								String shuliang=String.valueOf(model.getValueAt(i, 2)) ;
								String data1[]= {String.valueOf(timeNew) , cai,shuliang,"0"};
								Mysqld.upDate("insert into s_food (d_foodid,d_name,d_count,d_sta,d_time)VALUES(?,?,?,?,now())", data1);
							}
							Tools.messageWindows("添加成功");
							
							
							
							
						}
						
						
						
						
					}else {
						if(a==0) {
							Tools.messageWindows("厅位正在被占用,请选择其他厅位");
						}else
						if(a==2) {
							Tools.messageWindows("厅位正在被占用,请选择其他厅位");
						}else {
							Tools.messageWindows("不能重复点餐");
						}
						
					}
					
				}
				
				
			}
		});
		
	
		
		
		

		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedIndex()==0) {
					Tools.messageWindows("请选择厅位");
				}else {
					String ting=String.valueOf(comboBox.getSelectedItem());
					String data[]= {ting,user};
					ResultSet rs = Mysqld.QueryData("select * from s_seat where d_id=? and d_user=?", data);
					try {
						if(rs.next()) {
							//代表是我的厅位
							String da[]= {rs.getString("d_foodid")};
							ResultSet rs1 = Mysqld.QueryData("select s_food.d_name,s_dish.d_jiage,s_food.d_count,if(s_food.d_sta=0,'未上菜','已上菜') from s_food\r\n"
									+ "left JOIN s_dish on s_food.d_name=s_dish.d_name where s_food.d_foodid=?", da);
							Tools.addDataTable(rs1, model, 4);
						}else {
							Tools.messageWindows("您未在当前厅位点餐");
						}
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
			}
		});
	
		


	}
}
