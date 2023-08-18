package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mathworks.toolbox.javabuilder.MWException;
import com.sun.java.swing.SwingUtilities3;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xpath.internal.functions.FuncStringLength;

import MatlabDisplay.MatlabDisplayMCRFactory;
import Util.DbUtil;
import Util.StringUtil;
import dao.DecodeDao;
import dao.UserDao;
import model.User;
import model.WebfileChooser;
import java.awt.SystemColor;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWComplexity;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import MatlabDisplay.Display;

public class Decryption extends JFrame {

	private String name;
	private String password;
	private String power;
	private String location;
	private String hobby;
	private String email;
	private String work;
	private String workPlace;
	private String NickName;
	private String imageName;
	private Character s2=null;
	
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField lblNewLabel_5;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
	
	private DecodeDao sp = new DecodeDao();
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();
	private UserMainFrame userMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Decryption frame = new Decryption();
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
	public Decryption() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Decryption.class.getResource("/image/logo1.png")));
		
		setResizable(false);
		setTitle("对象解密");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 130, 1113, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
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
		
		JLabel lblNewLabel = new JLabel("密钥");
		lblNewLabel.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel.setBounds(103, 183, 78, 57);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_5 = new MyRoundJpassTextfield();
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_5.setBackground(new Color(249, 249, 249));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_5.setBackground(new Color(255, 255, 255));
			}
		});
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(186, 179, 395, 64);
		lblNewLabel_5.setMargin(new Insets(0, 5, 0, 5));
		lblNewLabel_5.setColumns(10);
        contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("解密对象");
		lblNewLabel_6.setFont(new Font("方正小标宋简体", Font.PLAIN, 28));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(270, 387, 141, 35);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(decodeActionPerformed(e)) {
						lblNewLabel_2_1.setText("解密状态：成功");
						
					}else{
						lblNewLabel_2_1.setText("解密状态：失败");
						
					};
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
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
		});
		btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
		btnNewButton_1.setBounds(192, 363, 310, 90);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("下载");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_6_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 28));
		lblNewLabel_6_1.setBounds(750, 474, 141, 35);
		contentPane.add(lblNewLabel_6_1);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_6.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_6.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					donloadActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
				btnNewButton_6.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_3.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnNewButton_6.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_2.png")));
			}
		});
		btnNewButton_6.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
		btnNewButton_6.setFocusable(false);
		btnNewButton_6.setContentAreaFilled(false);
		btnNewButton_6.setBorder(null);
		btnNewButton_6.setBounds(671, 450, 310, 90);
		contentPane.add(btnNewButton_6);
		
		 JLabel lblNewLabel_1 = new JLabel("");
	     lblNewLabel_1.setIcon(new ImageIcon(Decryption.class.getResource("/image/logo3.png")));
	     lblNewLabel_1.setBounds(649, 66, 381, 374);
	     contentPane.add(lblNewLabel_1);
	     
	     JLabel lblNewLabel_6_1_1 = new JLabel("预览");
	     lblNewLabel_6_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	     lblNewLabel_6_1_1.setForeground(Color.WHITE);
	     lblNewLabel_6_1_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 28));
	     lblNewLabel_6_1_1.setBounds(271, 474, 141, 35);
	     contentPane.add(lblNewLabel_6_1_1);
	     
	     JButton btnNewButton_6_1 = new JButton("");
	     btnNewButton_6_1.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
				try {
					Display imageDisplay = new Display();
					imageDisplay.ImageFinal(1,imageName);
				} catch (MWException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	     	}
	     });
	     btnNewButton_6_1.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseEntered(MouseEvent e) {
	     		btnNewButton_6_1.setIcon(new ImageIcon(Decryption.class.getResource("/image/圆角框_2.png")));
	     	}
	     	@Override
	     	public void mouseExited(MouseEvent e) {
	     		btnNewButton_6_1.setIcon(new ImageIcon(Decryption.class.getResource("/image/圆角框_1.png")));
	     	}
	     	@Override
	     	public void mousePressed(MouseEvent e) {
	     		btnNewButton_6_1.setIcon(new ImageIcon(Decryption.class.getResource("/image/圆角框_3.png"))); 	
	     	}
	     	@Override
	     	public void mouseReleased(MouseEvent e) {
	     		btnNewButton_6_1.setIcon(new ImageIcon(Decryption.class.getResource("/image/圆角框_2.png")));
	     	}
	     });
	     btnNewButton_6_1.setIcon(new ImageIcon(Decryption.class.getResource("/image/圆角框_1.png")));
	     btnNewButton_6_1.setFocusable(false);
	     btnNewButton_6_1.setContentAreaFilled(false);
	     btnNewButton_6_1.setBorder(null);
	     btnNewButton_6_1.setBounds(192, 450, 310, 90);
	     contentPane.add(btnNewButton_6_1);
	     
	     lblNewLabel_2 = new JLabel("解密级别：");
	     lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 26));
	     lblNewLabel_2.setForeground(new Color(128, 128, 128));
	     lblNewLabel_2.setBounds(115, 279, 310, 35);
	     contentPane.add(lblNewLabel_2);
	     
	     lblNewLabel_2_1 = new JLabel("解密状态：");
	     lblNewLabel_2_1.setForeground(Color.GRAY);
	     lblNewLabel_2_1.setFont(new Font("宋体", Font.PLAIN, 26));
	     lblNewLabel_2_1.setBounds(364, 279, 310, 35);
	     contentPane.add(lblNewLabel_2_1);
	     
	     JLabel lblNewLabel_3 = new JLabel("---------------------您的安全由我们来守护！---------------------");
	     lblNewLabel_3.setForeground(SystemColor.textHighlight);
	     lblNewLabel_3.setFont(new Font("优设标题黑", Font.PLAIN, 32));
	     lblNewLabel_3.setBounds(103, 642, 1104, 42);
	     contentPane.add(lblNewLabel_3);
		
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
	
	@SuppressWarnings("static-access")
	private void donloadActionPerformed(MouseEvent e) {
		String key = new String(this.lblNewLabel_5.getText());
		if(StringUtil.isEmpty(key)) {
			JOptionPane.showMessageDialog(null, "密钥不能为空！");
			return; 
		} 
		
		WebfileChooser webfileChooser = new WebfileChooser();
		webfileChooser.saveFile(key);	
	} 
	private boolean decodeActionPerformed(ActionEvent e) {
		String key = new String(this.lblNewLabel_5.getText());
		if(StringUtil.isEmpty(key)) {
			JOptionPane.showMessageDialog(null, "密钥不能为空！");
			return false; 
		} 
		try {
			if(sp.searchImg(key)) {
				String name,s1;
				name = sp.getImagename();
							
				for (int i = 0; i < name.length(); i++) {
			           if (Character.isDigit(name.charAt(i))) {
			              s2=name.charAt(i);
			           }
			    }
				imageName = name;
				System.out.println(name);
				
				if(s2 == '0') {
					lblNewLabel_2.setText("解密级别：机密");
				}else if(s2 == '1') {
					lblNewLabel_2.setText("解密级别：完全解密");
					
				}else if(s2 == '2') {
					lblNewLabel_2.setText("解密级别：透明");
					
				}else if(s2 == '3') {
					lblNewLabel_2.setText("解密级别：充分");
				}
				return true;
			}else {
				return false;
			}
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();			
		}
		return true;
	}
}
