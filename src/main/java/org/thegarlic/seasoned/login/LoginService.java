package org.thegarlic.seasoned.login;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thegarlic.seasoned.user.repository.User;
import org.thegarlic.seasoned.user.repository.UserRepository;
import org.thegarlic.util.PasswordUtil;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepo;
	
	public LoginResultVo login( String userId , String userPwd ) {
		LoginResultVo result = null;
		
		User user = userRepo.findByUserId( userId );
		
		if( null == user ) {
			result = new LoginResultVo();
			result.setSuccess( false );
			result.setResultCode( 1 );
			result.setMsg( "사용자가 존재하지 않습니다.");
			return result;
		}
		
		byte[] password = user.getPassword();
		
		
		byte[] passwordDigester = PasswordUtil.PasswordDigester(userId, userPwd);
		
		if( Arrays.equals(password, passwordDigester) ){
			// 패스워드 일치함. 인증 성공함.  사용자 정보를 세션에 저장하도록 하자. 
			result = new LoginResultVo();
			result.setSuccess( true );
			return result;
		} else {
			// 패스워드가 틀림.
			result = new LoginResultVo();
			result.setSuccess( false );
			result.setResultCode( 2 );
			result.setMsg("패스워드가 틀립니다.");
			return result;
		}
	}
	
	
	
	
}
