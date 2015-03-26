package org.thegarlic.seasoned.user.repository;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class User {

	
	
	// ����� id �� key �� �Ѵ�. 
	@Id
	private String userId;
	
	private String userFirstname;
	private String userLastname;

	
//	@Lob
	private byte[] password;
	
	public User() {
		
	}
	
	public User(String userId, String userFirstname, String userLastname , byte[] password ) {
		this.userId = userId;
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		
		this.setPassword(password);
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFirstname="
				+ userFirstname + ", userLastname=" + userLastname + "]";
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}
	
	
	
	
	
	
}
