package edu.kosmo.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import edu.kosmo.ex.vo.AuthVO;
import edu.kosmo.ex.vo.EmpVO;

@Mapper
public interface EmpMapper {

	@Select("select * from emp where ename = #{ename}")
	public EmpVO readUser(String name);

	@Select("select ename,case when job='MANAGER' then 'ROLE_ADMIN' else  'ROLE_USER' end authority from emp where ename = #{ename}")
	public List<AuthVO> readAuthority(String name);
}