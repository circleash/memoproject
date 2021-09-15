package com.circleash.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.circleash.memo.user.bo.UserBO;
import com.circleash.memo.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;

	@PostMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email) {
			 
		int count = userBO.addUser(loginId, password, name, email);
		
		Map<String, String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
			
		} else {
			result.put("result", "fail");
		}
		return result;
		
		}	
	
	@PostMapping("/sign_in")
	//Map은 view에 전달한 모델데이터를 설정할때 사용한다
	//클라이언트에서 전달받은 id, password를 다시 로직을타고 usermodel에 있는지 확인하고 그 값을 userBO를 통해 가져오고 다시 user에 저장.
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request) {
			User user = userBO.getUser(loginId, password);
			
			
			//로그인 성공 실패 여부
			//셀렉트 결과가 있냐 없냐?
			//셀렉트 결과가 있다
			Map<String, String> result = new HashMap<>();
		
			if(user != null) {
				//null일때 오류 방지
				//세션을 위한 공간을 만들고 로그인 아이디, 이름을 넣어둠. 정보가 항상 저장되어있다.
				//세션을 어디서든 가져다 쓸수있음.
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("loginId", user.getLoginId());
				session.setAttribute("userName", user.getName());
				
				result.put("result", "success");
			} else {
				result.put("result", "fail");
			}
			return result;
	}
}
