package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.StringUtils;
 
 
public class JTextFieldHintListener implements FocusListener {
 
	private String hintText;
 
	private JTextField textField;
	
 
	public JTextFieldHintListener(JTextField jTextField,String hintText) {
 
		this.textField = jTextField;
 
		this.hintText = hintText;
		
		jTextField.setText(hintText);  //默认直接显示
 
		jTextField.setForeground(Color.GRAY);
 
	}
 
 
 
	@Override
	public void focusGained(FocusEvent e) {
 
		//获取焦点时，清空提示内容
		String temp = textField.getText();
 
		if(temp.equals(hintText)) {
 
			textField.setText("");
 
			textField.setForeground(Color.BLACK);
			
			if(textField instanceof JPasswordField){
				((JPasswordField) textField).setEchoChar('●');
				((JPasswordField) textField).setFont(new Font("方正小标宋简体", Font.PLAIN, 16));
			}
		}
	}
 
 
 
	@Override
	public void focusLost(FocusEvent e) {	
		//失去焦点时，没有输入内容，显示提示内容
		String temp = textField.getText();
		if(StringUtils.isNullOrEmpty(temp)) {
			if(textField instanceof JPasswordField){
				((JPasswordField) textField).setEchoChar((char)0);
				((JPasswordField) textField).setFont(new Font("宋体", Font.PLAIN, 24));
			}
			
			textField.setForeground(Color.GRAY);
				
			textField.setText(hintText);
			
		}
	}
 
}