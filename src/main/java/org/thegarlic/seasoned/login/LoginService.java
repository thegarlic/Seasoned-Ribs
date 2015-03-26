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
			result.setMsg( "����ڰ� �������� �ʽ��ϴ�.");
			return result;
		}
		
		byte[] password = user.getPassword();
		
		
		byte[] passwordDigester = PasswordUtil.PasswordDigester(userId, userPwd);
		
		if( Arrays.equals(password, passwordDigester) ){
			// �н����� ��ġ��. ���� ������.  ����� ������ ���ǿ� �����ϵ��� ����. 
			result = new LoginResultVo();
			result.setSuccess( true );
			return result;
		} else {
			// �н����尡 Ʋ��.
			result = new LoginResultVo();
			result.setSuccess( false );
			result.setResultCode( 2 );
			result.setMsg("�н����尡 Ʋ���ϴ�.");
			return result;
		}
	}
	
	
	
	
}
