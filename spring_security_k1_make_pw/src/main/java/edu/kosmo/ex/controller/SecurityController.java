package edu.kosmo.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@RequestMapping("/security/*")//컨텍스트명 + security로 들어오는 모든 것들은 여기서 처리하라.
@Controller
public class SecurityController {
	
	@GetMapping("/all")
	public String all() {
		System.out.println("do all acess everybody");
		return "/security/all";
	}
	@GetMapping("/member")
	public String member() {
		System.out.println("logined member");
		return "/security/member";
	}
	@GetMapping("/admin")
	public void admin() {
		log.info("admin()...");
	}
	@GetMapping("/accessError")
	//return "/security/admin";
	public void accessError(Model model) {	
		log.info("/accessError..");		
		model.addAttribute("msg", "ACCESS Denied(403 에러)");
	}
}