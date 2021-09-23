package com.circleash.memo.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.circleash.memo.post.bo.PostBO;
import com.circleash.memo.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/create_view")
	public String createView() {
		
		return "post/createView";
	}
	
	@GetMapping("/list_view")
	public String listView(
			Model model
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		//세션의 userId를 저장하는 과정
		int userId = (Integer)session.getAttribute("userId");
		//userId를 토대로 BO DAO Mapper를 통해 다시 BO로 오는 과정으로 정보를 가져와 List형태로 담아준다.
		List<Post>memoList = postBO.getMemoList(userId);
		
		model.addAttribute("memoList", memoList);
		
		return "post/listView";
	}
	
	@GetMapping("/detail_view")
	public String detailView(
			@RequestParam("id") int id
			, Model model
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		Post memo = postBO.getMemo(id, userId);
		
		model.addAttribute("memo", memo);
		
		return "post/detailView";
	}
}
