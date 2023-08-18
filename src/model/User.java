package model;

import javax.print.DocFlavor.STRING;

public class User {

	private int id;
	private String userName;
	private String password;
	private String power;
	private String location;
	private String hobby;
	private String email;
	private String work;
	private String workPlace;
	private String NickName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public User(String userName, String password, String power) {
		super();
		this.userName = userName;
		this.password = password;
		this.power = power;
	}
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public void setLocation(String location) {
		this.location=location;
	}
	public void setHobby(String hobby) {
		this.hobby=hobby;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setWork(String work) {
		this.work=work;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace=workPlace;
	}
	public void setNickname(String nickName) {
		this.NickName=nickName;
	}
	public String getLocation() {
		return location;
	}
	public String getHobby() {
		return hobby;
	}
	public String getEmail() {
		return email;
	}
	public String getWork() {
		return work;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public String getNickname() {
		return NickName;
	}
}
