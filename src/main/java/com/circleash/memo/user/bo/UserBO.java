package com.circleash.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.circleash.memo.common.EncryptUtills;
import com.circleash.memo.user.dao.UserDAO;
import com.circleash.memo.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public int addUser(String loginId, String password, String name, String email) {
		//Password 암호화 
		
		String encryptPassword = EncryptUtills.md5(password);
		
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
		
	}
	//사용자 정보 다 들고올것임
	public User getUser(String loginId, String password) {
		String encryptPassword = EncryptUtills.md5(password);
		return userDAO.selectUserByLoginIdPassword(loginId, encryptPassword);
	}

}
