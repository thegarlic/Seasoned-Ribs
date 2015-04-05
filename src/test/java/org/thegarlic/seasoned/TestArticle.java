package org.thegarlic.seasoned;

import static org.junit.Assert.*;

import java.util.Date;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thegarlic.seasoned.article.repository.Article;
import org.thegarlic.seasoned.article.repository.ArticleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoStudyApplication.class)
@WebAppConfiguration
public class TestArticle {

	private static final String CONTENTS = "최초의 본문 내용";
	private static final String TITLE = "첫번째 글쓰기 테스트";
	@Autowired
	private ArticleRepository articleRepository;
	
	
	@Test
	public void test글쓰기테스트() {
		
		// 글 전체 삭제 
		articleRepository.deleteAll();
		
		// 갯수 0 확인 
		assertTrue( articleRepository.count() == 0 ); 
		
		Article art1 = new Article();
		art1.setTitle( TITLE);
		art1.setContents( CONTENTS);
		art1.setRegDate( new Date() );
		
		articleRepository.save(art1);
		
		Article findOne = articleRepository.findOne(1L);
		
		// 글 등록 확인 
		assertEquals( findOne.getTitle() , TITLE );
		assertEquals( findOne.getContents() , CONTENTS );
		
		// 전체 갯수 확인
		assertTrue( articleRepository.count() == 1 );
	}
	
}
