package org.thegarlic.seasoned;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thegarlic.seasoned.article.repository.Article;
import org.thegarlic.seasoned.article.repository.ArticleRepository;
import org.thegarlic.seasoned.user.repository.User;
import org.thegarlic.seasoned.user.repository.UserRepository;


/**
 *	사용자 글쓰기에 대한 테스트  
 * 
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoStudyApplication.class)
@WebAppConfiguration
public class TestUserArticle {

	private static final String CONTENTS = "최초의 본문 내용";
	private static final String TITLE = "first 글쓰기 테스트";
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Before
	public void setUp(){
		articleRepo.deleteAll();
		userRepo.deleteAll();
	}
	
	
	
	@Test
	public void test유저A가_글을쓰는_테스트() {
		
		// 갯수 0 확인 
		assertTrue( articleRepo.count() == 0 ); 
		assertTrue( userRepo.count() == 0 );

		// 테스트 사용자 추가
		User user1 = new User("testUser1", "testUserFM", "testUserLM", "test".getBytes() ); 
		userRepo.save(user1);
		
		// 게시글 추가 
		Article art1 = new Article();
		art1.setTitle( TITLE);
		art1.setContents( CONTENTS);
		art1.setRegDate( new Date() );
		
		// article 에 저자를 user1 객체로 지정
		art1.setAuther(user1);				
		
		articleRepo.save(art1);
		
		
		Article findOne = articleRepo.findOne(1L);
		
		// 글 등록 확인 
		assertEquals( findOne.getTitle() , TITLE );
		assertEquals( findOne.getContents() , CONTENTS );
		
		// 전체 갯수 확인
		assertTrue( articleRepo.count() == 1 );
		
		// data jpa 를 통하여 게시글 사용자의 id 를 이용하여 목록을 가져옴.
		Iterable<Article> articleByTestUser1 = articleRepo.findByAutherUserId("testUser1");
		
		for (Article article : articleByTestUser1) {
			System.out.println( article );
		}
		

		
		
		
	}
	
}
