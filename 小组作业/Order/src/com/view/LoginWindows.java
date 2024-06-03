package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import com.dao.AdminDao;
import com.tools.Tools;
import com.util.DBUtilZ;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindows {

	private JFrame frmJava;
	public static JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBUtilZ db=new DBUtilZ("root","","db_order");//数据库账号  密码 数据库名字
					LoginWindows window = new LoginWindows();
					window.frmJava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJava = new JFrame();
		frmJava.setIconImage(Toolkit.getDefaultToolkit().getImage("img/title.png"));
		frmJava.setTitle("java订餐管理系统");
		frmJava.setBounds(100, 100, 944, 410);
		frmJava.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmJava.getContentPane().setLayout(null);
		frmJava.setResizable(false);
		Tools.setWindowPos(612, 410, frmJava);//让窗口剧中
		
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(78, 131, 55, 38);
		frmJava.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(161, 142, 229, 21);
		frmJava.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(78, 190, 55, 30);
		frmJava.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("安全登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//按钮的点击事件
				//获取账号以及密码  进行登录
				String account=textField.getText();//获取账号
				String password=new String(passwordField.getPassword());
				if(account.equals("")) {
					Tools.messageWindows("请输入账号");
				}else  if(password.equals("")) {
					Tools.messageWindows("请输入密码");
				}else {
				 	AdminDao adminDao = new AdminDao();
					String res = adminDao.LoginAdmin(account, password);//res 0代表失败  1管理员  2普通用户
					
					if(res.equals("1")) {
						ManagerWindows window = new ManagerWindows();
						window.frame.setVisible(true);
						frmJava.dispose();
					}else if(res.equals("2")) {
						UserWindows window = new UserWindows();
						window.frame.setVisible(true);
						frmJava.dispose();
					}else {
						Tools.messageWindows("账号或密码错误，请输入正确账号密码？");
					}
				}
				
				
				
				
			}
		});
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.setForeground(new Color(128, 128, 255));
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 21));
		btnNewButton.setBounds(108, 256, 145, 38);
		frmJava.getContentPane().add(btnNewButton);
		

		
		passwordField = new JPasswordField();
		passwordField.setBounds(161, 197, 229, 21);
		frmJava.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("java订餐管理系统");
		lblNewLabel_4.setForeground(new Color(0, 128, 192));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(-17, 33, 578, 69);
		frmJava.getContentPane().add(lblNewLabel_4);
		
		

		
		JButton btnNewButton_1 = new JButton("注册账号");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//打开一个注册窗口
				RegisterWindows window = new RegisterWindows();
				window.frame.setVisible(true);
				
			}
		});
		btnNewButton_1.setForeground(new Color(128, 128, 255));
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 21));
		btnNewButton_1.setBackground(new Color(135, 206, 250));
		btnNewButton_1.setBounds(277, 256, 157, 38);
		frmJava.getContentPane().add(btnNewButton_1);
		
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("img/Login.jpg"));
		lblNewLabel_3.setBounds(0, 0, 813, 379);
		frmJava.getContentPane().add(lblNewLabel_3);
	}
}
