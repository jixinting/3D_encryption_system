package com.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Main extends Library {
	Main myDll = (Main)Native.load("My3DSteganography", Main.class);
	Main myDll_obj = (Main)Native.load("My3DSteganography_obj", Main.class);
 
	void image_encode(String file, String file0, String file1, String file2, String file3);
	void obj2off(String filename); 
	String conf_key();
	String clear_key();
	String trans_key();
	String suff_key();
	
	void image_encode_obj(String file, String file0, String file1, String file2, String file3);
	String conf_key_obj();
	String clear_key_obj();
	String trans_key_obj();
	String suff_key_obj();
}

