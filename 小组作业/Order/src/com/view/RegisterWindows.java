package com.view;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import com.bean.AdminBean;
import com.dao.AdminDao;
import com.tools.Tools;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class RegisterWindows {

	 JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindows window = new RegisterWindows();
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
	public RegisterWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img/title.png"));
		frame.setTitle("java订餐管理系统");
		frame.setBounds(100, 100, 579, 410);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 165, 0));
		lblNewLabel.setBounds(125, 124, 87, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 165, 0));
		lblNewLabel_1.setBounds(125, 164, 87, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("确认密码");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(255, 165, 0));
		lblNewLabel_2.setBounds(125, 209, 87, 39);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField.setBounds(222, 124, 170, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		passwordField.setBounds(222, 167, 170, 35);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		passwordField_1.setBounds(222, 212, 170, 39);
		frame.getContentPane().add(passwordField_1);
		
		JPanel panel = new JPanel();
		//设置边框线颜色 名字
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "注册订餐系统账号", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		panel.setForeground(Color.ORANGE);
		panel.setBounds(114, 10, 346, 353);
		frame.getContentPane().add(panel);
		panel.setOpaque(false);//设置透明
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("账号注册");
		lblNewLabel_3.setForeground(new Color(255, 69, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("幼圆", Font.BOLD, 33));
		lblNewLabel_3.setBounds(10, 22, 326, 78);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("注册账号");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//要进行注册账号
				String account=textField.getText();
				//String password=t
				String pas=Tools.getPassword(passwordField);
				String pass=Tools.getPassword(passwordField_1);
			
				if(account.equals("")) {
					Tools.messageWindows("请输入账号");
					
				}else if(pas.equals("")) {
					Tools.messageWindows("请输入密码");
					
				}else if(pass.equals("")) {
					Tools.messageWindows("请输入确认密码");
					
				}else if(!pass.equals(pas)) {
					Tools.messageWindows("两次密码不一致");
					
				}else {
					//可以进行注册账号了
					AdminBean admin=new AdminBean();//建立一个bean
					admin.setAccount(account);
					admin.setPassword(pass);
					int a=new AdminDao().RegistAdmin(admin);
					if(a==0||a==-1) {
						Tools.messageWindows("账号或者密码已经存在，请重新注册");
					}else {
						Tools.messageWindows("注册成功");
					}
				}
			}
		});
		btnNewButton.setBackground(new Color(173, 216, 230));
		btnNewButton.setForeground(new Color(218, 165, 32));
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 21));
		btnNewButton.setBounds(109, 257, 165, 41);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("img/regist.jpg"));
		lblNewLabel_4.setBounds(-21, -21, 626, 423);
		frame.getContentPane().add(lblNewLabel_4);
		frame.setResizable(false);
		//Tools.setWindowPos(612, 410, frame);//让窗口剧中
		
		
		
	}
}

