package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.awt.Toolkit;
import javax.swing.JTextArea;

public class RegFrame extends JFrame {

	private JPanel contentPane;
	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();
	
	private JTextField user_jtextField;
	private JPasswordField pass_jpassField;
	private JPasswordField rePass_jpassField;
	private JButton loginButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegFrame frame = new RegFrame();
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
	public RegFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegFrame.class.getResource("/image/logo1.png")));
		setResizable(false);
		setTitle("3D隐写系统V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 130, 1113, 745);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("欢迎注册3D隐写系统");
		lblNewLabel.setForeground(new Color(0, 0, 205));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lblNewLabel.setBounds(201, 128, 388, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("您的安全由我们来守护");
		lblNewLabel_2.setForeground(new Color(0, 0, 205));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(201, 190, 277, 33);
		contentPane.add(lblNewLabel_2);
		
		user_jtextField = new MyRoundJtextField();
		user_jtextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				user_jtextField.setBackground(new Color(242, 242, 242));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				user_jtextField.setBackground(new Color(255, 255, 255));
			}
		});
		user_jtextField.setFont(new Font("宋体", Font.PLAIN, 24));
		user_jtextField.setBounds(201, 279, 449, 46);
		user_jtextField.addFocusListener(new JTextFieldHintListener(user_jtextField, "用户名"));
		user_jtextField.setMargin(new Insets(0, 10, 0, 10));
		getContentPane().add(user_jtextField);
		
		pass_jpassField = new MyRoundJpassTextfield();
		pass_jpassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pass_jpassField.setBackground(new Color(242, 242, 242));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pass_jpassField.setBackground(new Color(255, 255, 255));
			}
		});
		pass_jpassField.setFont(new Font("宋体", Font.PLAIN, 24));
		pass_jpassField.setBounds(201, 360, 449, 46);
		pass_jpassField.addFocusListener(new JTextFieldHintListener(pass_jpassField, "密码"));
		pass_jpassField.setMargin(new Insets(0, 10, 0, 10));
		pass_jpassField.setEchoChar((char)0);
		getContentPane().add(pass_jpassField);
		
		rePass_jpassField = new MyRoundJpassTextfield();
		rePass_jpassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rePass_jpassField.setBackground(new Color(242, 242, 242));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rePass_jpassField.setBackground(new Color(255, 255, 255));
			}
		});
		rePass_jpassField.setFont(new Font("宋体", Font.PLAIN, 24));
		rePass_jpassField.setBounds(201, 440, 449, 46);
		rePass_jpassField.addFocusListener(new JTextFieldHintListener(rePass_jpassField, "确认密码"));
		rePass_jpassField.setMargin(new Insets(0, 10, 0, 10));
		rePass_jpassField.setEchoChar((char)0);
		getContentPane().add(rePass_jpassField);
		
		loginButton = new RoundRectButton();
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setBackground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(new Color(30, 144, 255));
			}
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regActionPerformed(e);
			}
		});
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setText("立即注册");
		loginButton.setBounds(201, 529, 449, 52);
		loginButton.setBackground(new Color(30, 144, 255));
		loginButton.setFont(new Font("方正小标宋简体", Font.BOLD, 26));
		loginButton.setFocusable(false);
		loginButton.setBorder(null);
		getContentPane().add(loginButton);
		
		JButton btnNewButton_2 = new JButton("返回");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 24));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/返回_2.png")));
				btnNewButton_2.setForeground(new Color(136, 136, 137));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/返回_1.png")));
				btnNewButton_2.setForeground(new Color(195, 196, 196));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				new LoginFrame().setVisible(true);
			}
		});
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/返回_1.png")));
		btnNewButton_2.setForeground(new Color(195, 196, 196));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBounds(10, 25, 153, 57);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setFocusable(false);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel_1.setIcon(new ImageIcon(RegFrame.class.getResource("/image/注册账号.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1110, 709);
		contentPane.add(lblNewLabel_1);
		
		this.setLocationRelativeTo(null);
	}
	
	private void regActionPerformed(ActionEvent e) {
		String userName=this.user_jtextField.getText();
		String password=new String(this.pass_jpassField.getPassword());
		String repass=new String(this.rePass_jpassField.getPassword());
		
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		if (StringUtil.isEmpty(repass)) {
			JOptionPane.showMessageDialog(null, "未确认密码！");
			return;
		}
		if (repass.equals(password)) {
			User user = new User(userName, password);
			Connection con=null;
			
			try {
				con=dbUtil.getCon();
				User current=userDao.query(con, userName);
				if(current==null)
				{
					int currentUser=userDao.addUser(con, user);
					if (currentUser==1) {
						JOptionPane.showMessageDialog(null, "注册成功！");
						dispose();
						new LoginFrame().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "注册失败！");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "该用户名已被注册，请更换用户名！");
				}
			}catch (Exception e2) {
					// TODO: handle exception
				e2.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		
		}
		else {
			JOptionPane.showMessageDialog(null, "两次密码不一致");
		}
	}
}
