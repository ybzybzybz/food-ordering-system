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
import com.mysqld.Mysqld;
import com.tools.Table;
import com.tools.Tools;

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

public class DishManageT extends JPanel {

	/**
	 * Create the panel.
	 */
	public DishManageT() {
		setLayout(null);
		this.setBounds(10, 10,899, 499);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "厅位状态", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 153)));
		panel.setBounds(10, 10, 866, 63);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("厅位");
		lblNewLabel.setBounds(5, 24, 59, 15);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1_1 = new JButton("查看厅位菜品");
		
		btnNewButton_1_1.setBounds(239, 20, 164, 23);
		panel.add(btnNewButton_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"请选择厅位"}));
		comboBox.setBounds(74, 20, 143, 23);
		panel.add(comboBox);
		ResultSet rs = new SeatDao().showSeat();
		//第一次查询所有的 然后再过滤
		try {
			while(rs.next()) {
				String res=rs.getString("d_sta");
				if(res.equals("1")) {//1代表正在进餐的
					comboBox.addItem(rs.getString("d_id"));
				}
			}
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("（查看你当前卓的上菜与未上传的情况）");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(423, 24, 272, 15);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("刷新厅位");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.removeAllItems();
				comboBox.addItem("请选择厅位");
				ResultSet rs = new SeatDao().showSeat();
				//第一次查询所有的 然后再过滤
				try {
					while(rs.next()) {
						String res=rs.getString("d_sta");
						if(res.equals("1")) {//1代表正在进餐的
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
		btnNewButton.setBounds(731, 20, 97, 23);
		panel.add(btnNewButton);
		
		
		
		
		
		
		//________________________________________________________
		
		Object columns[] ={"菜品名字","菜品价格","菜品数量","菜品状态"};//创建表格	
		Table t1Table=new Table(columns);
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(800,280));//设置整个滚动条窗口的大小
		JS.setBounds(10, 144, 866, 400);
		add(JS);
	
	
		
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				if(comboBox.getSelectedIndex()==0) {
					Tools.messageWindows("请选择厅位");
				}else {
					String ting=String.valueOf(comboBox.getSelectedItem());
					String data[]= {ting};
					ResultSet rs = Mysqld.QueryData("select * from s_seat where d_id=?", data);
					try {
						if(rs.next()) {
							//代表是我的厅位
							String da[]= {rs.getString("d_foodid")};
							ResultSet rs1 = Mysqld.QueryData("select s_food.d_name,s_dish.d_jiage,s_food.d_count,if(s_food.d_sta=0,'未上菜','已上菜') from s_food\r\n"
									+ "left JOIN s_dish on s_food.d_name=s_dish.d_name where s_food.d_foodid=?", da);
							Tools.addDataTable(rs1, model, 4);
						}else {
							Tools.messageWindows("厅位不存在");
						}
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
				
			}
		});

		
		
		
		
		
		
		
		
		
		
		//添加点餐的监听  选中进行点餐
		t1Table.getTables().addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable table =(JTable) e.getSource();
				if(comboBox.getSelectedIndex()==0) {
					Tools.messageWindows("请选择厅位");
				}else {
					
					
					 int r= table.getSelectedRow();
		              String cainame= table.getValueAt(r, 0).toString();//菜的名字
		              String ting= String.valueOf(  comboBox.getSelectedItem());//
		              String data[]= {ting,cainame};
		           
		              int a=Mysqld.upDate("update s_food set d_sta='1' where d_foodid=(select d_foodid from s_seat where d_id=?) and d_sta='0' and d_name=?",data);
		              if(a==1) {
		            	  Tools.messageWindows("上菜成功");
		              }else {
		            	  Tools.messageWindows("切勿重复上菜");
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
		
		
		
		
	}
}

