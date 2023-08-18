package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.DbUtil;
import dao.UserDao;
import model.User;

public class UserMainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table;

	private String name;
	private String password;
	private String power;
	private String location;
	private String hobby;
	private String email;
	private String work;
	private String workPlace;
	private String NickName;
	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();
	
	private SettingsFrame settings=new SettingsFrame();
	private LoginFrame login;
	private EncryptionFrame encryption;
	private Decryption decryption;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainFrame frame = new UserMainFrame();
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
	public UserMainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserMainFrame.class.getResource("/image/logo1.png")));
		setResizable(false);
		setTitle("3D隐写V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 130, 1113, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/对象加密_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/对象加密_1.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				encryption=new EncryptionFrame();
				encryption.init(name, password);
				encryption.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/对象加密_1.png")));
		btnNewButton.setBounds(157, 511, 143, 183);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(null);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/对象解密_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/对象解密_1.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				decryption=new Decryption();
				decryption.init(name, password);
				decryption.setVisible(true);
			}
		});
		btnNewButton_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnNewButton_1.setFocusable(false);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/对象解密_1.png")));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(490, 522, 146, 174);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusable(false);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1_1.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/个人信息_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1_1.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/个人信息_1.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if(name==null)
				{
					login=new LoginFrame();
					login.setVisible(true);
				}
				else {
					settings.init(name, password);
					settings.Data();
					settings.setVisible(true);
				}
				
			}
		});	
		
		btnNewButton_1_1.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/个人信息_1.png")));
		btnNewButton_1_1.setBorder(null);
		btnNewButton_1_1.setBackground(Color.WHITE);
		btnNewButton_1_1.setBounds(824, 517, 145, 179);
		btnNewButton_1_1.setContentAreaFilled(false);
		contentPane.add(btnNewButton_1_1);
		
		JLabel logo = new JLabel("3D隐写");
		logo.setForeground(new Color(0, 0, 153));
		logo.setFont(new Font("优设标题黑", Font.PLAIN, 30));
		logo.setIcon(null);
		logo.setBounds(94, 21, 196, 69);
		contentPane.add(logo);
		
		JLabel lblNewLabel_1 = new JLabel("已保护       天");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(910, 347, 145, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("   版本：1.0.0");
		lblNewLabel_2.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/下载.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(910, 384, 165, 46);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("22");
		lblNewLabel_3.setForeground(new Color(0, 51, 153));
		lblNewLabel_3.setFont(new Font("优设标题黑", Font.PLAIN, 26));
		lblNewLabel_3.setBounds(987, 348, 74, 40);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/logo1.png")));
		lblNewLabel_4.setBounds(10, 21, 74, 69);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(UserMainFrame.class.getResource("/image/UserMain.png")));
		lblNewLabel.setBounds(0, 0, 1113, 711);
		contentPane.add(lblNewLabel);
		

		this.setLocationRelativeTo(null);
		
	}
	
	void init(String Name, String password)
	{
		Connection con=null;
		
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.query(con, Name);
			if(currentUser!=null) {
				this.name=Name;
				this.password=currentUser.getPassword();
				this.power=currentUser.getPower();
				this.location=currentUser.getLocation();
				this.hobby=currentUser.getHobby();
				this.email=currentUser.getEmail();
				this.work=currentUser.getWork();
				this.workPlace=currentUser.getWorkPlace();
				this.NickName=currentUser.getNickname();
//				System.out.println(this.name);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}
