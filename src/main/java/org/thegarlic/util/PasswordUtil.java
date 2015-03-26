package org.thegarlic.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * 패스워드를 저장할 때에는 평문을 저장하지 않는다.
 * 
 * user id 를 salt 로 하여 패스워드와 합친 후, sha-256 단방향 해시함수를 사용하여 digest 를 만들어낸다.
 * 
 * @author smlee
 *
 */
public class PasswordUtil {

	
	/**
	 * 패스워드 유틸
	 * 
	 * @param userId   사용자 id
	 * @param pwd      사용자 pwd
	 * @return         사용자 ID + PWD 를 이용하여 SHA-256 으로 해시한 BYTE 배열을 반환 
	 */
	public static byte[] PasswordDigester( String userId , String pwd ) {
		
		String tmp = userId + pwd;
		System.out.println( "TMP : " + tmp );
		try {
			MessageDigest instance = MessageDigest.getInstance("SHA-256");
			byte[] digest = instance.digest( tmp.getBytes() );
			return digest;
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 을 지원하지 않을줄이야....", e);
		}
	}

	
}
