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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jna.algorithm;
import com.mathworks.toolbox.javabuilder.MWException;

import Util.DbUtil;
import Util.StringUtil;
import dao.DecodeDao;
import dao.UserDao;
import model.User;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWComplexity;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.sun.org.apache.xpath.internal.functions.FuncSubstring;

import MatlabDisplay.Display;

public class EncryptionFrame extends JFrame {
	
	//****************用户信息******************
	private String name;
	private String password;
	private String power;
	private String location;
	private String hobby;
	private String email;
	private String work;
	private String workPlace;
	private String NickName;
	//******************************************
	
	private static final long serialVersionUID = 1L;
    private JButton btnNewButton;
	private JPanel contentPane1;
	private boolean flag;
	private JPanel contentPane;
	private JTextField firstTxt;
	private JTextField secondTxt;
	private JTextField thirdTxt;
	private JTextField fourTxt;
	Icon icon=null;
	private Character s2=null;
	private String imageName;
	
	
	private DecodeDao sp = new DecodeDao();
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();
	private JFileChooser chooser = new JFileChooser();
	private UserMainFrame userMain;
	
//	imageDisplay = new Display();
	
	private algorithm algorithm=new algorithm();
	private String filepath = "";
	private String imagename = "";
	String[] str3=new String[] {"","","",""};
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptionFrame frame = new EncryptionFrame();
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
	public EncryptionFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EncryptionFrame.class.getResource("/image/logo1.png")));
		
		//**********************加密主界面************************
		setResizable(false);
		setTitle("对象加密");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 130, 1113, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		contentPane.setLayout(null);
		setContentPane(contentPane);
		flag=false;
		//*******************************************************
		
		//***********************添加图片*************************
		btnNewButton= new JButton("");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(icon==null)
				{
					btnNewButton.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/添加图片_2.png")));
					btnNewButton.setBounds(140, 131, 340, 318);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(icon==null)
				{
					btnNewButton.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/添加图片_1.png")));
					btnNewButton.setBounds(140, 131, 340, 318);
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser.setMultiSelectionEnabled(false); // 设为多选
				int returnVal = chooser.showOpenDialog(btnNewButton); // 是否打开文件选择框
				System.out.println("returnVal=" + returnVal);
				if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型
					filepath = chooser.getSelectedFile().getAbsolutePath(); // 获取绝对路径

					System.out.println(filepath);
					JOptionPane.showMessageDialog(null, "你选择打开文件: " + filepath); // 输出绝对路径
					imagename = (String)chooser.getSelectedFile().getName();
				}
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/添加图片_1.png")));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(140, 131, 340, 318);
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(null);
		contentPane.add(btnNewButton);
		//*******************************************************
		
		//***********************加密图像*************************
		JLabel lblNewLabel = new JLabel("加密对象");
		lblNewLabel.setFont(new Font("方正小标宋简体", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(246, 530, 141, 35);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_2.png")));
			}
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
			}
			public void mousePressed(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_3.png")));
			}
			public void mouseReleased(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_2.png")));
				if(filepath=="")
					JOptionPane.showMessageDialog(null, "请先选择您要加密的文件！");
				else {		
					String endStr = filepath.substring(filepath.lastIndexOf(".")+1);
					System.out.println(endStr);
					JOptionPane.showMessageDialog(null,"图像加密中……");
					if(endStr.equals("off")) {
						str3=algorithm.encode_main(filepath);
					}
					else {
						System.out.println("endStr != off");
					}
					if(endStr.equals("obj")) {
						str3=algorithm.encode_main_obj(filepath);
					}
					else {
						System.out.println("endStr != obj");
					}
					JOptionPane.showMessageDialog(null,"图像加密完成！");
					
					firstTxt.setText(str3[0]);
					secondTxt.setText(str3[1]);
					thirdTxt.setText(str3[2]);
					fourTxt.setText(str3[3]);
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
		btnNewButton_1.setBounds(170, 505, 310, 90);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		contentPane.add(btnNewButton_1);
		//*****************************************************
		
		//***************************预览**************************
		JLabel lblNewLabel_1 = new JLabel("预   览");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("方正小标宋简体", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(780, 520, 86, 57);
		contentPane.add(lblNewLabel_1);
		//*********************************************************
		
		JButton btnNewButton_3 = new JButton("");
//		btnNewButton_3.addActionListener(new ActionListener() {
//	     	public void actionPerformed(ActionEvent e) {
//				try {
//					String newname = watchActionPerformed(e, imagename);
//					System.out.println("111111111111111111111111111111111");
//					Display imageDisplay = new Display();
//					System.out.println("222222222222222222222222222222222");
//					
//					imageDisplay.ImageFinal(1, newname);
//				} catch (MWException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//	     	}
//	     });
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnNewButton_3.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_2.png")));
			}
			public void mouseExited(MouseEvent e) {
				btnNewButton_3.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
			}
			public void mousePressed(MouseEvent e) {
				btnNewButton_3.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_3.png")));
				
				chooser.setMultiSelectionEnabled(false); // 设为多选
				int returnVal = chooser.showOpenDialog(btnNewButton); // 是否打开文件选择框
				System.out.println("returnVal=" + returnVal);
				if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型
					filepath = chooser.getSelectedFile().getAbsolutePath(); // 获取绝对路径
					imagename = (String)chooser.getSelectedFile().getName();
				}
				try {
					Display imageDisplay = new Display();
					imageDisplay.ImageFinal(1,imagename);
				} catch (MWException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
			public void mouseReleased(MouseEvent e) {
				btnNewButton_3.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_2.png")));
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/圆角框_1.png")));
		btnNewButton_3.setBounds(668, 505, 310, 90);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.setBorder(null);
		contentPane.add(btnNewButton_3);
		
		//************************返回**************************
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
		//*********************************************************
		
		
		//***********************四个解密密钥***********************
		JLabel lblNewLabel_5 = new JLabel("密钥：");
		lblNewLabel_5.setFont(new Font("方正小标宋简体", Font.PLAIN, 30));
		lblNewLabel_5.setBounds(587, 87, 98, 37);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("机密级别：");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 26));
		lblNewLabel_6.setBounds(587, 177, 141, 37);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("原始模型：");
		lblNewLabel_6_1.setFont(new Font("宋体", Font.PLAIN, 26));
		lblNewLabel_6_1.setBounds(587, 245, 130, 37);
		contentPane.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("透明级别：");
		lblNewLabel_6_1_1.setFont(new Font("宋体", Font.PLAIN, 26));
		lblNewLabel_6_1_1.setBounds(587, 311, 130, 37);
		contentPane.add(lblNewLabel_6_1_1);
		
		 JLabel lblNewLabel_7 = new JLabel("充分级别：");
	     lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 26));
	     lblNewLabel_7.setBounds(587, 380, 141, 38);
	     contentPane.add(lblNewLabel_7);
		     
        firstTxt = new MyRoundJtextField();
        firstTxt.setEditable(false);
        firstTxt.setFont(new Font("宋体", Font.PLAIN, 15));
        firstTxt.setBounds(729, 177, 310, 35);
        firstTxt.setMargin(new Insets(0, 5, 0, 5));
        contentPane.add(firstTxt);
        firstTxt.setColumns(10);
        
        secondTxt = new MyRoundJtextField();
        secondTxt.setEditable(false);
        secondTxt.setFont(new Font("宋体", Font.PLAIN, 15));
        secondTxt.setBounds(729, 245, 310, 35);
        secondTxt.setMargin(new Insets(0, 5, 0, 5));
        contentPane.add(secondTxt);
        secondTxt.setColumns(10);
        
        thirdTxt = new MyRoundJtextField();
        thirdTxt.setEditable(false);
        thirdTxt.setFont(new Font("宋体", Font.PLAIN, 15));
        thirdTxt.setBounds(729, 313, 310, 35);
        thirdTxt.setMargin(new Insets(0, 5, 0, 5));
        contentPane.add(thirdTxt);
        thirdTxt.setColumns(10);
        
        fourTxt = new MyRoundJtextField();
        fourTxt.setEditable(false);
        fourTxt.setFont(new Font("宋体", Font.PLAIN, 15));
        fourTxt.setBounds(729, 381, 310, 37);
        fourTxt.setMargin(new Insets(0, 5, 0, 5));
        contentPane.add(fourTxt);
        fourTxt.setColumns(10);
        //**********************************************************
        
        //***************************背景***************************
        JLabel lblNewLabel_8 = new JLabel("New label");
        lblNewLabel_8.setIcon(new ImageIcon(EncryptionFrame.class.getResource("/image/背景.jpg")));
        lblNewLabel_8.setBounds(0, 0, 1114, 715);
        contentPane.add(lblNewLabel_8);
        //**********************************************************

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
	
	private String watchActionPerformed(ActionEvent e, String name) {
		String key = new String(this.firstTxt.getText());
		if(StringUtil.isEmpty(key)) {
			JOptionPane.showMessageDialog(null, "请选择对象进行加密！");
		} 
		String s1;
		s1=name.substring(0,name.indexOf("."));
		name = (String)s1+"_0.txt";
		
		try {
			sp.readImg(name, key);
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return name;
	}
}
