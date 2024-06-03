package com.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.jpan.UserDianOrder;
import com.tools.Tools;

public class UserWindows {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UserWindows window = new UserWindows();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(153, 255, 51));
		frame.getContentPane().setBackground(new Color(204, 255, 153));
		frame.setBounds(100, 100, 943, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Tools.setWindowPos(943, 566, frame);//让窗口剧中
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img/title.png"));
		frame.setTitle("java订餐管理系统");
		
		
		JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP);
		tabPane.setBackground(new Color(204, 255, 255));
		
		UserDianOrder dishManage = new UserDianOrder();
		dishManage.setBackground(new Color(255, 255, 255));
		tabPane.add("菜品管理",dishManage);
		

		
		
		tabPane.setBounds(20, 10, 899, 499);
		frame.getContentPane().add(tabPane);
		
		
		
		
		
	}
}

