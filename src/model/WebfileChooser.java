package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.DecodeDao;

public class WebfileChooser {
	
	static DecodeDao sp = new DecodeDao();
	
	public static void main(String[] args) {
		//fileChooserOpen();
//		saveFile();
	}
	public static void fileChooserOpen() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("3D文件(*.obj, *.off)", "obj", "off"); //文件类型
		chooser.setFileFilter(filter);//打开选择器面板
		int returnVal = chooser.showOpenDialog(new JPanel()); //保存文件从这里入手，输出的是文件名
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("你打开的文件是: "+chooser.getSelectedFile().getName());
		}
	}

	public static void fileChooserClose (String imageFileName){
		javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
		chooser.setFileSelectionMode(javax.swing.JFileChooser.SAVE_DIALOG);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to download your image to this file: " +chooser.getSelectedFile().getPath());
		}

	}
	
	public static void saveFile(String key) {
		//弹出文件选择框
		JFileChooser chooser = new JFileChooser();
		
		//后缀名过滤器
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "3D文件(*.obj, *.off)", "obj", "off");
		chooser.setFileFilter(filter);
		
		//下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
		int option = chooser.showSaveDialog(null);
		if(option==JFileChooser.APPROVE_OPTION){	//假如用户选择了保存
			File file = chooser.getSelectedFile();
			
			String fname = chooser.getName(file);	//从文件名输入框中获取文件名
			
//			假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
//			if(fname.indexOf(".obj")==-1){
//				fname = fname+".obj";
//			}
			String filename = chooser.getCurrentDirectory()+"\\"+fname;
			try {
				sp.readImg(filename, key);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
				
		}
	}

}
