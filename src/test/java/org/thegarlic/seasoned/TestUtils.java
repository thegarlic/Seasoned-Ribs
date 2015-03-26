package org.thegarlic.seasoned;

import org.junit.Assert;
import org.junit.Test;
import org.thegarlic.util.PasswordUtil;

public class TestUtils {


	@Test
	public void 패스워드는_아이디를_salt_로해서_패스워드의_hash값을_얻어온다() {
		String userId = "test1";
		String pwd = "0123456789";
		
		String userId2 = "test2";
		
		
		
		byte[] pwdRes1 = PasswordUtil.PasswordDigester( userId , pwd );
		byte[] pwdRes2 = PasswordUtil.PasswordDigester( userId2 , pwd );
		
		System.out.println( "pwdRes1 : " + new String( pwdRes1 ) );
		System.out.println( "pwdRes2 : " + new String( pwdRes2 ) );
		
		Assert.assertNotEquals( pwdRes1 , pwdRes2 );
		
	}


}
