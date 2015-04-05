package org.thegarlic.seasoned.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, String> {

	// 사용자 id 를 이용하여 사용자를 찾는다.
	User findByUserId( String userId );
	
	
	
	
}
