package edu.kosmo.ex.service;


import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import edu.kosmo.ex.mapper.EmpMapper;
import edu.kosmo.ex.vo.EmpVO;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class EmpService {
	
	@Inject
	private EmpMapper empMapper;
	
	public EmpVO getUser(String ename){
		log.info("readUser .. ");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user_id=auth.getName();
		System.out.println("유저 아이디: " + user_id);
		
		EmpVO empVO = empMapper.readUser(ename);
		
		empVO.setAuthList(empMapper.readAuthority(ename));
		return empVO;
	}
	

}