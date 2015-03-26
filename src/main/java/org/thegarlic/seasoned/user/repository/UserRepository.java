package org.thegarlic.seasoned.user.repository;

import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, String> {

	User findByUserId( String userId );
	
}
