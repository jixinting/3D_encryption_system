package com.jna;

import com.sun.jna.Platform;

import dao.DecodeDao;

//public abstract class algorithm implements Runnable {
public class algorithm {
	
	static DecodeDao decodeDao=new DecodeDao();
	
	public static void main(String[] args) {
		System.out.println(Platform.isWindows());
		
		String fl = "D:\\Algorithm_test\\teapot.off";
		String[] str2=new String[] {"","","",""};
		str2=encode_main(fl);

	}
    
    public static String[] encode_main(String filepath) {
    	
		String s1 = filepath.substring(0,filepath.indexOf("."));
    	
    	String filepath0=(String)s1+"_0.txt";
		String filepath1=(String)s1+"_1.txt";
		String filepath2=(String)s1+"_2.txt";
		String filepath3=(String)s1+"_3.txt";
		
		Main.myDll.image_encode(filepath, filepath0, filepath1, filepath2, filepath3);
		String conf_key=Main.myDll.conf_key();
		conf_key=conf_key.substring(0, 64);
		String clear_key=Main.myDll.clear_key();
		clear_key=clear_key.substring(0, 64);
		String trans_key=Main.myDll.trans_key();
		trans_key=trans_key.substring(0, 64);
		String suff_key=Main.myDll.suff_key();
		suff_key=suff_key.substring(0, 64); 

    	String[] str= new String[]{conf_key,clear_key,trans_key,suff_key};
		
		try {
			decodeDao.storeImg(filepath0,conf_key);
			decodeDao.storeImg(filepath1,clear_key);
			decodeDao.storeImg(filepath2,trans_key);
			decodeDao.storeImg(filepath3,suff_key);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
		
    }
    
	public static String[] encode_main_obj(String filepath) {
	    	
		String s1 = filepath.substring(0,filepath.indexOf("."));
    	
    	String filepath0=(String)s1+"_0.txt";
		String filepath1=(String)s1+"_1.txt";
		String filepath2=(String)s1+"_2.txt";
		String filepath3=(String)s1+"_3.txt";
		
		Main.myDll_obj.image_encode_obj(filepath, filepath0, filepath1, filepath2, filepath3);
		String conf_key=Main.myDll_obj.conf_key_obj();
		conf_key=conf_key.substring(0, 64);
		String clear_key=Main.myDll_obj.clear_key_obj();
		clear_key=clear_key.substring(0, 64);
		String trans_key=Main.myDll_obj.trans_key_obj();
		trans_key=trans_key.substring(0, 64);
		String suff_key=Main.myDll_obj.suff_key_obj();
		suff_key=suff_key.substring(0, 64); 

    	String[] str= new String[]{conf_key,clear_key,trans_key,suff_key};
		
		try {
			decodeDao.storeImg(filepath0,conf_key);
			decodeDao.storeImg(filepath1,clear_key);
			decodeDao.storeImg(filepath2,trans_key);
			decodeDao.storeImg(filepath3,suff_key);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
			
	}
	
	public static void obj_to_off(String filepath) {
		Main.myDll.obj2off(filepath);
	}
}