package edu.kosmo.ex.vo;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@Setter
public class UserCustom extends User {
	
	@Setter(onMethod_ = @Autowired)
	private UserVO user;
	   
	public UserCustom(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserCustom(UserVO user) {

		super(user.getUsername(), user.getPassword(), getAuth(user));

		this.user = user;
	}

	public static Collection<? extends GrantedAuthority> getAuth(UserVO memberVO) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (AuthVO auth : memberVO.getAuthList()) {
			authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
		}

		return authorities;
	}
}
