package dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import model.User;

public class UserDao {

	public User login(Connection con, User user)throws Exception{
		User resultUser=null;
		String sql="select*from user where userName=? and password=?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			resultUser = new User();
//			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setPower(rs.getString("power"));
		}
		
		return resultUser;
	}
	
	/*
	 * 
	 * 用户注册
	 * 
	 */
	public int addUser(Connection con, User user)throws Exception{
		String sql="insert into user values(null, ?, ?, ?, null, null, null, null, null, null)";
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, "员工");
		
		return pstmt.executeUpdate();
	}
	
	public User query(Connection con, String name)throws Exception {
		User resultUser=null;
		String sql="select*from user where userName=?";

		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			resultUser = new User();
//			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setPower(rs.getString("power"));
			resultUser.setLocation(rs.getString("location"));
			resultUser.setHobby(rs.getString("hobby"));
			resultUser.setEmail(rs.getString("email"));
			resultUser.setWork(rs.getString("work"));
			resultUser.setWorkPlace(rs.getString("workPlace"));
			resultUser.setNickname(rs.getString("NickName"));
		}
		
		return resultUser;
	}
	public int change(Connection con,User user)throws Exception {
		String sql="update user set password=?,power=?,NickName=?,location=?,hobby=?,email=?,work=?,workPlace=? where userName=?";
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getPower());
		pstmt.setString(3, user.getNickname());
		pstmt.setString(4, user.getLocation());
		pstmt.setString(5, user.getHobby());
		pstmt.setString(6, user.getEmail());
		pstmt.setString(7, user.getWork());
		pstmt.setString(8, user.getWorkPlace());
		pstmt.setString(9, user.getUserName());
		System.out.println(user.getUserName());
		System.out.println(user.getNickname());
		return pstmt.executeUpdate();
	}
}
