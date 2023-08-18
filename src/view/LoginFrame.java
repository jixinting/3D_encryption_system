package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Util.DbUtil;
import Util.StringUtil;
import dao.UserDao;
import model.User;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private JLabel lblNewLabel;
	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();
	private SettingsFrame frame=new SettingsFrame();
	private UserMainFrame userMain=new UserMainFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/image/logo1.png")));
		setResizable(false);
		setTitle("3D隐写V1.0");
		setBounds(100, 100, 680, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userNameTxt = new JTextField();
		userNameTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/图片2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/图片1.png")));
			}
		});
		userNameTxt.setFont(userNameTxt.getFont().deriveFont(userNameTxt.getFont().getSize() + 16f));
		userNameTxt.setHorizontalAlignment(SwingConstants.LEFT);
		userNameTxt.setBounds(157, 214, 425, 42);
		userNameTxt.setBorder(null);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/图片3.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/图片1.png")));
			}
		});
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 28));
		passwordTxt.setBackground(new Color(255, 255, 255));
		passwordTxt.setForeground(new Color(0, 0, 0));
		passwordTxt.setHorizontalAlignment(SwingConstants.LEFT);
		passwordTxt.setBounds(157, 283, 425, 42);
		passwordTxt.setBorder(null);
		contentPane.add(passwordTxt);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(new Color(51, 102, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(new Color(102, 153, 255));
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(new Color(102, 153, 255));
		btnNewButton.setFont(new Font("方正小标宋简体", Font.BOLD, 30));
		btnNewButton.setBounds(95, 368, 486, 50);
		btnNewButton.setBorder(null);
		btnNewButton.setFocusable(false);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("立即注册");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(new Color(113, 113, 113));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(new Color(153, 153, 153));
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegFrame().setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(153, 153, 153));
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(10, 439, 93, 23);
		btnNewButton_1.setBorder(null);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("3D隐写");
		lblNewLabel_1.setFont(new Font("华文中宋", Font.BOLD, 26));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(27, 20, 162, 50);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 28));
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/图片1.png")));
		lblNewLabel.setBounds(0, 0, 681, 503);
		contentPane.add(lblNewLabel);
		
		
		
		this.setLocationRelativeTo(null);
	}
	
	/*
	 * 
	 * 登录事件处理
	 * 
	 */
	private void loginActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String userName = this.userNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		
		User user = new User(userName, password);
		Connection con=null;
		
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser!=null) {
				dispose();
				if(currentUser.getPower().equals("管理员"))
					new AdminMainFrame().setVisible(true);
				else {
					userMain.init(currentUser.getUserName(), currentUser.getPassword());
					userMain.setVisible(true);
				}
					
			}
			else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
			}
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
			
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}
}
