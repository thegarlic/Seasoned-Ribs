package org.thegarlic.seasoned.article.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long>{

	// 저자의 id 를 이용하여 게시글의 목록을 가져온다.
	Iterable<Article> findByAutherUserId( String userId );
	
	
}
