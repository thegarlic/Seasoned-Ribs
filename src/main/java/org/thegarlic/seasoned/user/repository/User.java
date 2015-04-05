package org.thegarlic.seasoned.user.repository;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.thegarlic.seasoned.article.repository.Article;



@Entity
public class User {

	
	
	// 사용자 id 를 key 로 한다. 
	@Id
	private String userId;
	
	private String userFirstname;
	private String userLastname;

	

	private byte[] password;
	
	@OneToMany( fetch= FetchType.LAZY)
	private List<Article> articles;
	
	
	
	
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
