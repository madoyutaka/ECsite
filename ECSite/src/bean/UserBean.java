package bean;

import java.io.Serializable;

public class UserBean implements Serializable {
	int userNo;
	String userId = null;
	String password = null;
	String emailAddress = null;
	int postalCode;
	String address = null;
	String userName = null;
	
 public UserBean(String userId,String password,String emailAddress,int postalCode,String address,String userName) {
	 this.userId=userId;
	 this.password=password;
	 this.emailAddress=emailAddress;
	 this.postalCode = postalCode;
	 this.address=address;
	 this.userName=userName;
	 }


	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}


