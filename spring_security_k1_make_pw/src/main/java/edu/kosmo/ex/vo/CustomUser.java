package edu.kosmo.ex.vo;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.kosmo.ex.mapper.EmpMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
public class CustomUser extends User {
   
   @Setter(onMethod_ = @Autowired)
   private EmpVO emp;
   
   //기본적으로 부모의 생성자를 호출해야만 정상적으로 작동
   public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
      super(username, password, authorities);
      
   }
   
   public CustomUser(EmpVO empVO) {
      super(empVO.getEname(), Integer.toString(empVO.getEmpno()),getAuth(empVO));
      // TODO Auto-generated constructor stub
      this.emp = empVO;
   }
   
   //유저가 갖고 있는 권한 목록
   public static Collection<? extends GrantedAuthority> getAuth(EmpVO empVO) { 

      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
      
      for (AuthVO auth: empVO.getAuthList()) {
         authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
      }
      
      return authorities;
   }   
}