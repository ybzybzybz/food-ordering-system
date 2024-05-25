package com.jpan;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DishManage extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public DishManage() {
		setLayout(null);
		this.setBounds(10, 52, 894, 462);
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 875, 150);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("菜品名称");
		lblNewLabel.setBounds(5, 8, 48, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(58, 5, 88, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("菜品价格");
		lblNewLabel_1.setBounds(5, 33, 48, 15);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(58, 33, 88, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("菜品口味");
		lblNewLabel_2.setBounds(5, 58, 48, 15);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(58, 55, 88, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("菜品详情\r\n");
		lblNewLabel_3.setBounds(5, 83, 48, 15);
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(58, 80, 88, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("菜品图片");
		lblNewLabel_4.setBounds(5, 108, 58, 15);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("选择图片");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(58, 104, 88, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("图片显示");
		btnNewButton_1.setBounds(197, 8, 115, 115);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("菜品名称（操作）");
		lblNewLabel_5.setBounds(385, 8, 147, 15);
		panel.add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(542, 5, 136, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("增加菜品");
		btnNewButton_2.setBounds(385, 54, 81, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("删除菜品");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_1.setBounds(385, 104, 81, 23);
		panel.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("更改菜品");
		btnNewButton_2_2.setBounds(542, 54, 88, 23);
		panel.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_3 = new JButton("查询菜品");
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_3.setBounds(533, 104, 97, 23);
		panel.add(btnNewButton_2_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 212, 874, 207);
		add(panel_1);

	}
}
