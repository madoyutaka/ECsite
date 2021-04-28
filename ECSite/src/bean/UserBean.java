package bean;

import java.io.Serializable;

		public class UserBean implements Serializable {
		 private String user_id;
		 private String password;
		 private String email_address ;
	 	 private int postal_code;
		 private String address;
		 private String user_name;

		  public UserBean(String user_id,String password,String email_address,int postal_code,String address,String user_name) {
			 this.user_id=user_id;
			 this.password=password;
			 this.email_address=email_address;
			 this.postal_code = postal_code;
			 this.address=address;
			 this.user_name=user_name;

		 }


			public void setUser_id(String newUser_id) {
				if (newUser_id!=null) {
				user_id = newUser_id;
				}
				}
				public String getUser_id() {
				return user_id;
				}



			public void setPassword(String newPassword) {
				if (newPassword!=null) {
				password = newPassword;
				}
				}
				public String getPassword() {
				return password;
				}



			public void setEmail_address(String newEmail_address) {
				if (newEmail_address!=null) {
				email_address = newEmail_address;
				}
				}
				public String getEmail_address() {
				return email_address;
				}


			public void setPostal_code(int newPostal_code) {
				if (newPostal_code!=0) {
					postal_code = newPostal_code;
				}
				}
				public int getPostal_code() {
				return postal_code;
				}


			public void setAddress(String newAddress) {
				if (newAddress!=null) {
					address = newAddress;
				}
				}
				public String getAddress() {
				return address;
				}


			public void setUser_name(String newUser_name) {
				if (newUser_name!=null) {
				user_name = newUser_name;
				}
				}
				public String getUser_name() {
					return user_name;
				}




	}


