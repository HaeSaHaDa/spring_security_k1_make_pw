package edu.kosmo.ex.service;

import javax.inject.Inject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.kosmo.ex.mapper.UserMapper;
import edu.kosmo.ex.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service
public class UserService {
	//암호화 모듈
   @Inject
   private BCryptPasswordEncoder passEncoder;

   @Inject
   private UserMapper userMapper;

   //@Transactional(rollbackFor = Exception.class)
   public void addUser(UserVO userVO) {
      String password = userVO.getPassword();
      String encode = passEncoder.encode(password);//암호화 시키는 부분. 이렇게 인코드 시키면, 이상한 문자로 들어가.

      userVO.setPassword(encode); //암호화 시킨 것을 집어 넣고.

      userMapper.insertUser(userVO); //db에 인서트 시키는 거. 
      userMapper.insertAuthorities(userVO); 
   }
}