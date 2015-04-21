package org.thegarlic.seasoned.article.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Lazy;
import org.thegarlic.seasoned.user.repository.User;


/**
 * 게시 글 
 * 
 * @author home
 *
 */
@Entity 
public class Article {

	@Id
	@GeneratedValue( strategy= GenerationType.AUTO)
	Long id;
	
	
	String title;		// 제목
	
	
	String contents;	// 본문 내용 
	
	Date regDate;		// 등록 일자 


	@ManyToOne
	User auther;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public User getAuther() {
		return auther;
	}

	public void setAuther(User auther) {
		this.auther = auther;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", contents="
				+ contents + ", regDate=" + regDate + ", auther=" + auther
				+ "]";
	}

	
	
	
	
	
}
