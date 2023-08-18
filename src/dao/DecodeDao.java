package dao;

import java.io.*;
import java.sql.*;

import javax.swing.JOptionPane;

import org.w3c.dom.html.HTMLTableCaptionElement;

import com.jna.algorithm;
public class DecodeDao {
	
    private String dbDriver;
    private String dbURL;
    private String dbUser;
    private String dbPassword;
    private Connection con;
    private PreparedStatement ps;  
    private String imagename;
    private algorithm algorithm=new algorithm();
    /**
   	 * 构造函数，初始化数据库的连接
   	 * 
   	 */
    
    public static void main(String[] args) throws Exception {
    	DecodeDao sp = new DecodeDao();
    	String imgPath="test.off";
        sp.storeImg(imgPath,"111");
//        sp.readImg("D://数据库.off", Ky);  //这里的1为要传入的参数：读取文件的id
//        sp.readImg("D://数据库.docx", 8);
    }
    
    public DecodeDao() {
        dbDriver = "com.mysql.jdbc.Driver";
//      dbURL = "jdbc:mysql://47.120.36.105:3306/infosystem";
        dbURL = "jdbc:mysql://localhost:3306/infosystem";
        dbUser = "root";
        dbPassword = "123456";
//        initDB();
    }  
    public DecodeDao(String strDriver, String strURL,
            String strUser, String strPwd) {
        dbDriver = strDriver;
        dbURL = strURL;
        dbUser = strUser;
        dbPassword = strPwd;
        initDB();
    }
 
    @SuppressWarnings("deprecation")
	public void initDB() {
        try {
            // Load Driver
            Class.forName(dbDriver).newInstance();
            // Get connection
            con = DriverManager.getConnection(dbURL,
                    dbUser, dbPassword);           
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
	 * 将指定路径的文件(比如：图片，word文档等)存储到数据库
	 * @param strFile 要存放到数据库的文件路径，如D:\\a.jpg
	 */
    public void storeImg(String strFile,String ky) throws Exception {
    	 	initDB();
            File file = new File(strFile);
            FileInputStream fis = new FileInputStream(file);           
            try {              
                ps = con.prepareStatement("insert into PIC values (null,?,?,?)");
                //ps.setInt(1, id);
                ps.setString(1,ky);
                ps.setString(2, file.getName());
                ps.setBinaryStream(3, fis, (int) file.length());
                ps.executeUpdate();  
                System.out.println("file insert success ");
            } catch (SQLException e) {
                System.out.println("SQLException: "
                        + e.getMessage());
                System.out.println("SQLState: "
                        + e.getSQLState());
                System.out.println("VendorError: "
                        + e.getErrorCode());
                e.printStackTrace();
            } finally {            
                ps.close();
                fis.close();
                con.close();
            }       
    }
    /**
	 * 将存储在数据库中的文件(比如：图片，word文档等)读取到指定路径
	 * @param path  从数据库里读取出来的文件存放路径 如D:\\a.jpg
	 * @param id    数据库里记录的id
	 */
    public void readImg(String filename, String ky) throws Exception{
    	initDB();   //建立与数据库的连接
    	byte[] buffer = new byte[4096];
    	FileOutputStream outputImage = null;
    	InputStream is = null;
    	ResultSet rs;
    	File file = new File(filename);
    	if (!file.exists()) {
  	      file.createNewFile();   	    
  	    }
    	String endStr = filename.substring(filename.lastIndexOf(".")+1);
    	try {
    		String sql= "select * from pic where ky=?";
    	    ps = con.prepareStatement(sql);
    	    ps.setString(1,ky);
    	    rs= ps.executeQuery();

    	    if(rs.next()) {
	    	    outputImage = new FileOutputStream(file);    	       	    	
	    	    Blob blob = rs.getBlob("img");   //img为数据库存放图片字段名称
	    	    is = blob.getBinaryStream();    	   
	    	    int size = 0; 	   
	    	    while ((size = is.read(buffer)) != -1) {  	     
	    	    	outputImage.write(buffer, 0, size);   	     
	    	    }
	    	    if(endStr.equals("off")) {
	        		algorithm.obj_to_off(filename);
	        	}
	    	    JOptionPane.showMessageDialog(null, "下载成功！");
	    	    System.out.println("file read success ");
    	    }else {
    	    	System.out.println("密钥错误!");
    	    }
	    	    
    	} catch (Exception e) {
    		   e.printStackTrace();
    	} finally {
    	     is.close();
    	     outputImage.close();
    	     ps.close();
    	     con.close();
    	}
    }
   
    public boolean searchImg(String ky) throws Exception{
    	initDB();   //建立与数据库的连接
    	ResultSet rs;
    	try {
    		String sql= "select * from pic where ky=?";
    	    ps = con.prepareStatement(sql);
    	    ps.setString(1,ky);
    	    rs= ps.executeQuery();

    	    if(rs.next()) {
	    	    JOptionPane.showMessageDialog(null, "密钥正确！可预览图片！");
	    	    imagename=rs.getString(3);
	    	    return true;
    	    }else {
    	    	
    	    	JOptionPane.showMessageDialog(null, "密钥错误！");
    	    	return false;
    	    }
	    	    
    	} catch (Exception e) {
    		   e.printStackTrace();
    	} finally {
    	     ps.close();
    	     con.close();
    	}
    	return false;
    }
    public String getImagename() {
    	return imagename;
    }
}
