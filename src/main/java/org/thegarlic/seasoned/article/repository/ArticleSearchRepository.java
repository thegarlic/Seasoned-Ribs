package org.thegarlic.seasoned.article.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleSearchRepository extends ElasticsearchCrudRepository<Article, Long>{

	/**
	 * contents 에 들어있는 문자열을 대소문자 구분 없이 검색
	 * @param contents
	 * @return
	 */
	List<Article> findByContentsContainingIgnoreCase(String contents);
	
	Page<Article> findByContentsContainingIgnoreCase(String contents, Pageable pageable);
	
//	@Query("{\"bool\" : {\"must\" : {\"field\" : {\"contents\" : \"?0\"}}}}")
//    Page<Article> findByContentsContaining(String contents, Pageable pageable);
	
}
