package org.thegarlic.seasoned;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thegarlic.seasoned.login.LoginResultVo;
import org.thegarlic.seasoned.login.LoginService;
import org.thegarlic.seasoned.user.repository.User;
import org.thegarlic.seasoned.user.repository.UserRepository;
import org.thegarlic.util.PasswordUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoStudyApplication.class)
@WebAppConfiguration
public class TestLogin {

	
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private LoginService loginService;
	
	@Before
	public void setUp() {
		
		userRepo.save( new User( 	"user1" , 
									"Tom" , 
									"크루즈" , 
									PasswordUtil.PasswordDigester( "user1", "pwd1" )));
		
		userRepo.save( new User( 	"user2" , 
									"Anjellina" , 
									"졸리" , 
									PasswordUtil.PasswordDigester( "user2", "pwd2" ) ));
		
	}
	
	
	@Test
	public void 사용자목록출력하기() {
		
		Iterable<User> userAll = userRepo.findAll();
		
		int cnt = 0;
		for( User u : userAll ) {
			System.out.println( u.toString() );
			cnt ++ ;
		}
		
		assert cnt == 2;
	}
	
	@Test
	public void 로그인인증테스트_성공() {

		LoginResultVo user1Result = loginService.login("user1", "pwd1");
		
		Assert.assertTrue( user1Result.isSuccess() ); 
	}
	
	@Test
	public void 로그인인증테스트_아이디존재하지않음() { 
		LoginResultVo notExistResult = loginService.login("user12", "pwd1");
		Assert.assertFalse( notExistResult.isSuccess() );
		Assert.assertEquals( notExistResult.getResultCode()  , 1 );
	}
	
	
	@Test
	public void 로그인인증테스트_패스워드틀림() { 
		LoginResultVo notExistResult = loginService.login("user1", "wrongPassword");
		Assert.assertFalse( notExistResult.isSuccess() );
		Assert.assertEquals( notExistResult.getResultCode()  , 2 );
	}
	
	
}
