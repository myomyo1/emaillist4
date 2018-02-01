package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.EmaillistDao;
import com.javaex.vo.EmaillistVo;

@Controller
public class EmaillistController {
	
	@Autowired //자동연결! new역할 이제 new 없이 사용
	private EmaillistDao emaillistdao; 
	
//	@RequestMapping(value="/form", method=RequestMethod.GET) //주소정해준 것 : localhost:8088/emaillist3/form 
	@RequestMapping("/form") //위에 풀버전 약식
	public String form() {
		System.out.println("form");
		return "form";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(@ModelAttribute EmaillistVo emaillistvo) {  //DispatcherServlet이 @ModelAttribute EmaillistVo emaillistvo 얘를 보고 찾아뒤짐
		System.out.println(emaillistvo.toString());
		
//		* 이전방식		
//		EmaillistDao dao = new EmaillistDao();
//		dao.insert(emaillistvo); 
		emaillistdao.insert(emaillistvo);
//		return "list"; //포워드 된 것. but 데이터 없이. 아래와 같이 모델에 담아서 할 수 있는데 코드 중복되므로 리다이렉트시키는게 더 효율적 
		return "redirect:/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) { //모델에 담아서 DispatcherServlet에게 보낸 것.
		System.out.println("list");
		List<EmaillistVo> eList = emaillistdao.getList(); //받아오기
		model.addAttribute("eList",eList); //담아보내기
		return "list"; //데이터 받아와서 포워드 된 것.
	}

}
