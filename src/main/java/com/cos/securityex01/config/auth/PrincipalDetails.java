package com.cos.securityex01.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.securityex01.model.User;

import lombok.Data;

// Authentication 객체에 저장할 수 있는 유일한 타입

//시큐리티가 /loginProc 주소 요청이 오면 낚아채서 로그인을 진행시킨다
//로그인을 진행이 완료가 되면  시큐리티 세션을 만들어준다.(Security ContextHolder라는 이 키값에 세션을 저장)
//시큐리티가 가진 세션에 들어갈수 있는 오브젝트는 정해져있다 (Authentication타입 객체여야한다.)
//Authentication 안에 User정보가 있어야 된다.
//User오브젝트 타입은 UserDetails 타입 객체


//Security session에 들어갈수 있는 객체가 Authentication객체임. 여기에 유저정보를 저장할때 UserDetails타입이어햔다.


@Data
public class PrincipalDetails implements UserDetails{

	private User user;

	public PrincipalDetails(User user) {
		super();
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	//비번이 잠김
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비번만료
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		//우리 사이트에서 1년동안 회원이 로그인을 안하면 휴면 계정으로 하기로 했다면
		//현재시간 - 로긴시간 이 1년을 초과하면 return false
		
		return true;
	}
	
	
	//해당 유저의 권한을 리턴하는 곳 
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
//		collet.add(()->{ return user.getRole();});
//		return collet;
//	}
	
	
	//강의버전
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collet = new ArrayList<>();
		collet.add(new GrantedAuthority(){
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collet;
	}


	
}
