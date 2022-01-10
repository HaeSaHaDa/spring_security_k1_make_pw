package edu.kosmo.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import edu.kosmo.ex.vo.AuthVO;
import edu.kosmo.ex.vo.EmpVO;
import edu.kosmo.ex.vo.UserVO;

@Mapper
public interface UserMapper {
	   //요게 핵심
	   public UserVO getUser(String username);   
	   
	   @Insert("insert into users(username,password,enabled) values(#{username},#{password},#{enabled})")
	   public int insertUser(UserVO userVO);

	   @Insert("insert into AUTHORITIES (username,AUTHORITY) values(#{username},'ROLE_USER')")
	   public void insertAuthorities(UserVO UserVO);

}