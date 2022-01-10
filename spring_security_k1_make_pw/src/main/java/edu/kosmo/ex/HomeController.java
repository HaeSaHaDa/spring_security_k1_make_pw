package edu.kosmo.ex;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.kosmo.ex.vo.CustomUserDetails;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@GetMapping("/login/loginForm")//경로를 security-context의 설정과 맞춰줘
	public String loginForm() {//void는 리퀘스트 매핑에 써 있는 /security 이 함수이 /admin이 붙어서 리턴
		log.info("Welcome Login Form");
		return "login/loginForm2";
	}
	@GetMapping("/user/userHome")//경로를 security-context의 설정과 맞춰줘
	public String userHome() {//void는 리퀘스트 매핑에 써 있는 /security 이 함수이 /admin이 붙어서 리턴
		log.info("userHome()...");
		return "user/userHome";
	}
	@GetMapping("/admin/adminHome")//경로를 security-context의 설정과 맞춰줘
	public String adminHome() {//void는 리퀘스트 매핑에 써 있는 /security 이 함수이 /admin이 붙어서 리턴
		log.info("adminHome()...");
		return "/admin/adminHome";
	}
	@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
	   public String loginInfo(Principal principal,Model model) {
	      
			// 1.Controller를 통하여 Pincipal객체로 가져오는 방법
			String user_id = principal.getName();
			System.out.println("유저 아이디:" + user_id);

			// 2.SpringContextHolder를 통하여 가져오는 방법(일반적인 빈에서 사용 할수있음 )
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			user_id = auth.getName();
			System.out.println("유저 아이디:" + user_id);

			// 3.
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			System.out.println(userDetails.getUsername());

			// 4.
			CustomUserDetails couCustomUserDetails = (CustomUserDetails) auth.getPrincipal();
			System.out.println(couCustomUserDetails.getEmp());

			// 5.User 클래스로 변환 하여 가져오는 방법
			couCustomUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			user_id = couCustomUserDetails.getUsername();
			System.out.println("유저 아이디:" + user_id);

	        return "home";
	   }
}
