package com.circleash.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.circleash.memo.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("subject") String subject
			, @RequestParam("content") String content
			, @RequestParam("file") MultipartFile file
			, HttpServletRequest request) {
		
		//파일을 저장할때 같은 파일이름이 중복되서 파일 덮어써지거나 하는 문제 막기 위함임
		//파일을 바로 저장하는게 아닌 디렉토리를 만들어 중복을 피하려함.
		//사용자별 디렉토리 구분 --> 중복확률 다운되기 때문에 사용자 정보로 디렉토리 이름을 만드는 것
		//같은 사용자도 같은 파일 이름을 쓸수 있기 때문에 추가로 현재시간을 초로 나타내는걸 추가로 이름에 대한 문제 회피
		
		//로그인 로직을 만들때 만든 내용임. 저장해둔 내용을 꺼내쓴것
		HttpSession session = request.getSession();
		//사용자 세션 저장
		int userId = (Integer)session.getAttribute("userId");
		int count = postBO.addPost(userId, subject, content, file);
		
		
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		return result;
	}
	
	@GetMapping("/delete")
	public Map<String, String> delete(@RequestParam("postId") int postId
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> result = new HashMap<>();
		int count = postBO.deleteMemo(postId, userId);
		
		if(count == 0) {
			result.put("result", "fail");
		} else {
			result.put("result", "success");
		}
		return result;
		
	}
	//긴내용은 post가 좋음
	@PostMapping("/update")
	public Map<String, String> update(
			@RequestParam("postId") int postId
			, @RequestParam("subject") String subject
			, @RequestParam("content") String content
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> result = new HashMap<>();
		int count = postBO.updateMemo(postId, subject, content, userId);
		
		if(count == 0) {
			result.put("result", "fail");
		} else { 
			result.put("result", "success");
		}
		return result;
	}

}
