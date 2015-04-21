package org.thegarlic.seasoned;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thegarlic.seasoned.article.repository.Article;
import org.thegarlic.seasoned.article.repository.ArticleSearchRepository;


/**
 * 
 * example url
 * https://github.com/spring-projects/spring-data-elasticsearch
 *
 * gradle 에 elasticSearch 종속성 하나만 추가하면, 시작시에 노드를 검색한다. 
 * 설정이 없다면 알아서 실행을 시킨다. 
 * 
 * 테스트는 그렇게 하겠지만, 운용상에서는 서버 설정을 좀 해야하겠다. 안그러면 데이터가 영원히 기록되지 않겠지.... 
 * cluster 이름. ip : port 리스트등  
 * 
 * 
 * @author home
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoStudyApplication.class)
@WebAppConfiguration
public class TestElasticSearch {

	@Autowired
    private ArticleSearchRepository repository;
	
	@Test
	public void test_문서_2개를_추가하여_검색() {
		
		/**
		 * 문서 2개 추가 
		 */
		Article article = new Article();
		article.setTitle("Title1");
		article.setContents("this is about economic research");
		
		repository.save(article);
		
		article = new Article();
		article.setTitle("Title2");
		article.setContents("this is about technology research");
		
		repository.save(article);
		
		// 전체 문서 검색 
		Iterable<Article> allArticles = repository.findAll();
		
		// final 로 선언한 이유는 anonymous 함수로 구현한 내부에서 카운트를 계산하고싶어서임. 
		final AtomicInteger totalCount = new AtomicInteger();
		
		allArticles.forEach((Consumer<? super Article>) new Consumer<Article>() {

			@Override
			public void accept(Article t) {
				System.out.println( t.toString() );
				// totalCount++ 와 동일하다. 
				totalCount.incrementAndGet();
			}
		});
		
		System.out.println( "Total count : " + totalCount );
		
		Assert.assertEquals( totalCount.get() , 2 );
		
		/**
		 * Econo 라는 단어가 포함된 문서를 검색한다. 
		 * IgnoreCase 로 대소문자 구분을 없앤다. 
		 * 
		 *  elasticSearch 는 Containing 이 구현되어있다고 한다. 
		 */
		System.out.println( "=====================================================");
		System.out.println( "======= find by contents =========");
		
		List<Article> listContents = repository.findByContentsContainingIgnoreCase("Econo");

		final AtomicInteger totalCount2 = new AtomicInteger();
		for (Article article2 : listContents) {
			System.out.println( article2.toString() );
			totalCount2.incrementAndGet();
		}
	
		Assert.assertEquals( totalCount2.get() , 1 );

		/**
		 * Page 구현 테스트. 
		 * Research 라는 단어가 포함된 문서를 찾는다. 
		 */
		System.out.println( "======= find by contents with page =========");
		// page api 의 page 는 0 부터 시작함. 
		Pageable page = new PageRequest(0 , 10 );	// page , size 	
		Page<Article> pageContents = repository.findByContentsContainingIgnoreCase("Research", page);

		final AtomicInteger totalCount3 = new AtomicInteger();
		
		pageContents.forEach((Consumer<? super Article>) new Consumer<Article>() {

			@Override
			public void accept(Article t) {
				System.out.println( t.toString() );
				totalCount3.incrementAndGet();
			}
		});
		
		Assert.assertEquals( totalCount3.get() , 2 );
		
		
		
		
		
		
	}

}
