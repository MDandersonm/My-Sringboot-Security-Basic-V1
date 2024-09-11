package com.cos.securityex01.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.securityex01.model.User;
import com.cos.securityex01.repository.UserRepository;

//시큐리티 설정에서 loginProcessingUrl을 걸어놨기때문에
//해당 url로 로그인 요청이 오면 자동으로 UserDetailsService타입으로 IoC되어있는 loadUserByUsername 함수가 실행된다.


@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	//String username은  HTML의 input의 name="username" 과 일치시켜야한다
	//안그러고 username2로 바꾸고싶다면  시큐리티설정에 .usernameParameter("username2") 이렇게 설정해줘야한다.
	//UserDetails객체를 리턴 하게되면 Authentication 내부로 들어간다 그리고 이모든게 시큐리티 세션내로 들어간다.                          
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return null;
		}
		return new PrincipalDetails(user);
	}

}
