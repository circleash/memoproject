package com.circleash.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtills {
	// 암호화 메소드
	// static을 붙임으로 객체 생성 없이도 사용가능.
	public static String md5(String message) {
		String encData = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 1byte = 8bit = 10101011
			// ex)비밀번호 asdf -> a = 64 = 1000000
			// [1000000, 1010100, 1000111, 101011] 
			
			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			byte[] digest = md.digest();
			// byte -> 16진수 -> 문자열
			for(int i = 0; i < digest.length; i++) {
				// 6 7 a b e
				encData += Integer.toHexString(digest[i] & 0xff); //16진수로 바꾸는 연산
				
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		return encData;
		
	}
}
