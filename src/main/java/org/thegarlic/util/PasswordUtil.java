package org.thegarlic.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * �н����带 ������ ������ ���� �������� �ʴ´�.
 * 
 * user id �� salt �� �Ͽ� �н������ ��ģ ��, sha-256 �ܹ��� �ؽ��Լ��� ����Ͽ� digest �� ������.
 * 
 * @author smlee
 *
 */
public class PasswordUtil {

	
	/**
	 * �н����� ��ƿ
	 * 
	 * @param userId   ����� id
	 * @param pwd      ����� pwd
	 * @return         ����� ID + PWD �� �̿��Ͽ� SHA-256 ���� �ؽ��� BYTE �迭�� ��ȯ 
	 */
	public static byte[] PasswordDigester( String userId , String pwd ) {
		
		String tmp = userId + pwd;
		System.out.println( "TMP : " + tmp );
		try {
			MessageDigest instance = MessageDigest.getInstance("SHA-256");
			byte[] digest = instance.digest( tmp.getBytes() );
			return digest;
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 �� �������� �������̾�....", e);
		}
	}

	
}
