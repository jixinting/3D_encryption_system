package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Util.DbUtil;
import dao.UserDao;
import model.User;

public class SettingsFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_5_1;
	private JLabel lblNewLabel_6_1;
	private JLabel lblNewLabel_6_1_1;
	private JLabel lblNewLabel_6_1_1_1;
	private JLabel lblNewLabel_2_1_2;
	
	private String name;
	private String password;
	private String power;
	private String location;
	private String hobby;
	private String email;
	private String work;
	private String workPlace;
	private String NickName;
	
	private JTextArea id_text = new JTextArea();;
	private JTextArea NickName_text;
	private JTextArea power_text;
	private JTextArea location_text;
	private JTextArea hobby_text;
	private JTextArea email_text;
	private JTextArea work_text;
	private JTextArea workPlace_text;
	
	private UserMainFrame userMain;
	
	private int count=0;
	
	User user;
	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsFrame frame = new SettingsFrame();
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
	public SettingsFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SettingsFrame.class.getResource("/image/logo1.png")));
		setResizable(false);
		setTitle("个人中心");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 130, 1113, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				userMain=new UserMainFrame();
				userMain.init(name, password);
				userMain.setVisible(true);
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/头像.jpg")));
		lblNewLabel.setBounds(66, 406, 205, 205);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("头像");
		lblNewLabel_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(66, 631, 81, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(440, 99, 39, 31);
		contentPane.add(lblNewLabel_2);
		
		id_text.setEditable(false);
		id_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_3.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_1.png")));
			}
		});
		id_text.setFont(new Font("宋体", Font.PLAIN, 26));
		id_text.setText(String.valueOf(name));
		id_text.setBounds(505, 99, 474, 32);
		contentPane.add(id_text);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_1.png")));
		lblNewLabel_3.setBounds(494, 84, 512, 64);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("昵称");
		lblNewLabel_2_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_2_1.setBounds(415, 180, 60, 31);
		contentPane.add(lblNewLabel_2_1);
		
		NickName_text = new JTextArea();
		NickName_text.setEditable(false);
		NickName_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_1.png")));
			}
		});
		NickName_text.setFont(new Font("宋体", Font.PLAIN, 26));
		NickName_text.setBounds(504, 178, 177, 32);
		contentPane.add(NickName_text);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_1.png")));
		lblNewLabel_4.setBounds(494, 161, 238, 64);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("权限");
		lblNewLabel_2_1_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_2_1_1.setBounds(715, 178, 60, 31);
		contentPane.add(lblNewLabel_2_1_1);
		
		power_text = new JTextArea();
		power_text.setEditable(false);
		power_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_5.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_5.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_1.png")));
			}
		});
		power_text.setFont(new Font("宋体", Font.PLAIN, 26));
		power_text.setBounds(810, 177, 165, 32);
		contentPane.add(power_text);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_1.png")));
		lblNewLabel_5.setBounds(798, 161, 238, 64);
		contentPane.add(lblNewLabel_5);
		
		location_text = new JTextArea();
		location_text.setEditable(false);
		location_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_6.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_6.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_1.png")));
			}
		});
		location_text.setFont(new Font("宋体", Font.PLAIN, 26));
		location_text.setBounds(505, 253, 177, 32);
		contentPane.add(location_text);

		lblNewLabel_2_1_2 = new JLabel("所在地");
		lblNewLabel_2_1_2.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_2_1_2.setBounds(389, 256, 90, 31);
		contentPane.add(lblNewLabel_2_1_2);	

		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_1.png")));
		lblNewLabel_6.setBounds(494, 237, 238, 64);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_2_1_2_1 = new JLabel("爱好");
		lblNewLabel_2_1_2_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_2_1_2_1.setBounds(715, 256, 60, 31);
		contentPane.add(lblNewLabel_2_1_2_1);
		
		hobby_text = new JTextArea();
		hobby_text.setEditable(false);
		hobby_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_5_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_5_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_1.png")));
			}
		});
		hobby_text.setFont(new Font("宋体", Font.PLAIN, 26));
		hobby_text.setBounds(810, 255, 165, 32);
		contentPane.add(hobby_text);		

		lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/短输入框_1.png")));
		lblNewLabel_5_1.setBounds(798, 238, 238, 64);
		contentPane.add(lblNewLabel_5_1);
		
		email_text = new JTextArea();
		email_text.setEditable(false);
		email_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_6_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_6_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_1.png")));
			}
		});
		email_text.setFont(new Font("宋体", Font.PLAIN, 26));
		email_text.setBounds(505, 331, 474, 32);
		contentPane.add(email_text);

		lblNewLabel_6_1 = new JLabel("");
		lblNewLabel_6_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_1.png")));
		lblNewLabel_6_1.setBounds(494, 313, 494, 64);
		contentPane.add(lblNewLabel_6_1);

		JLabel lblNewLabel_2_1_2_2 = new JLabel("邮箱");
		lblNewLabel_2_1_2_2.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_2_1_2_2.setBounds(415, 328, 60, 31);
		contentPane.add(lblNewLabel_2_1_2_2);
		
		JLabel lblNewLabel_2_1_2_2_1 = new JLabel("职业");
		lblNewLabel_2_1_2_2_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_2_1_2_2_1.setBounds(415, 405, 60, 31);
		contentPane.add(lblNewLabel_2_1_2_2_1);
		
		work_text = new JTextArea();
		work_text.setEditable(false);
		work_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_6_1_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_6_1_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_1.png")));
			}
		});
		work_text.setFont(new Font("宋体", Font.PLAIN, 26));
		work_text.setBounds(505, 404, 474, 32);
		contentPane.add(work_text);

		lblNewLabel_6_1_1 = new JLabel("");
		lblNewLabel_6_1_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_1.png")));
		lblNewLabel_6_1_1.setBounds(494, 387, 494, 64);
		contentPane.add(lblNewLabel_6_1_1);
		
		JLabel lblNewLabel_2_1_2_2_1_1 = new JLabel("单位");
		lblNewLabel_2_1_2_2_1_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_2_1_2_2_1_1.setBounds(415, 477, 60, 31);
		contentPane.add(lblNewLabel_2_1_2_2_1_1);
		
		workPlace_text = new JTextArea();
		workPlace_text.setEditable(false);
		workPlace_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_6_1_1_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_6_1_1_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_1.png")));
			}
		});
		workPlace_text.setFont(new Font("宋体", Font.PLAIN, 26));
		workPlace_text.setBounds(505, 477, 474, 32);
		contentPane.add(workPlace_text);		

		lblNewLabel_6_1_1_1 = new JLabel("");
		lblNewLabel_6_1_1_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/image/长输入框_1.png")));
		lblNewLabel_6_1_1_1.setBounds(494, 461, 494, 64);
		contentPane.add(lblNewLabel_6_1_1_1);
		
		JLabel lblNewLabel_7 = new JLabel("编辑资料");
		lblNewLabel_7.setFont(new Font("方正小标宋简体", Font.PLAIN, 28));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBounds(656, 559, 141, 35);
		contentPane.add(lblNewLabel_7);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_3.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_2.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				count ^= 1;
				if(count%2==0)
				{
					SetInfo();
					lblNewLabel_7.setText("编辑资料");
					SetUnEdit();
				}
				else
				{
					lblNewLabel_7.setText("保  存");
					SetEdit();
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
		btnNewButton_1.setBounds(578, 535, 310, 90);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		contentPane.add(btnNewButton_1);
		
		 JLabel lblNewLabel_8 = new JLabel("New label");
	     lblNewLabel_8.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/背景.jpg")));
	     lblNewLabel_8.setBounds(0, 0, 1114, 715);
	     contentPane.add(lblNewLabel_8);
		
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
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	void Data() {
		id_text.setText(name);
		NickName_text.setText(NickName);
		power_text.setText(power);
		location_text.setText(location);
		hobby_text.setText(hobby);
		email_text.setText(email);
		work_text.setText(work);
		workPlace_text.setText(workPlace);
	}
	
	void SetEdit() {
		NickName_text.setEditable(true);
		power_text.setEditable(true);
		location_text.setEditable(true);
		hobby_text.setEditable(true);
		email_text.setEditable(true);
		work_text.setEditable(true);
		workPlace_text.setEditable(true);
	}
	
	void SetInfo() {
		Connection con=null;
		
		try {
			con=dbUtil.getCon();
			User currentUser=new User();
			currentUser.setUserName(id_text.getText());
			currentUser.setPassword(password);
			currentUser.setPower(power_text.getText());
			currentUser.setNickname(NickName_text.getText());
			currentUser.setLocation(location_text.getText());
			currentUser.setHobby(hobby_text.getText());
			currentUser.setEmail(email_text.getText());
			currentUser.setWork(work_text.getText());
			currentUser.setWorkPlace(workPlace_text.getText());
			
			int flag = userDao.change(con, currentUser);
			if(flag==1) {
				JOptionPane.showMessageDialog(null, "修改成功！");
			}
			else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	void SetUnEdit() {
		NickName_text.setEditable(false);
		power_text.setEditable(false);
		location_text.setEditable(false);
		hobby_text.setEditable(false);
		email_text.setEditable(false);
		work_text.setEditable(false);
		workPlace_text.setEditable(false);
	}
}
