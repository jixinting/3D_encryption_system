package Util;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * 
 * 连接数据库
 * 
 */
public class DbUtil {

	private String dbUrl="jdbc:mysql://47.122.7.71:3306/infosystem"; // 数据库地址
//	private String dbUrl="jdbc:mysql://localhost:3306/infosystem";
	private String dbUserName="root"; // 用户名
	private String dbPassword="123456"; // 登录密码
	private String jdbcName="com.mysql.jdbc.Driver"; //驱动器名称

	/*
	 * 
	 * 获取数据库连接
	 * 
	 */
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/*
	 * 
	 * 关闭数据库
	 * 
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null) {
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
