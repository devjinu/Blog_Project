package com.cos.blog_project.config.auth;

import com.cos.blog_project.model.User;
import com.cos.blog_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때, username + password 변수 2개를 가지고 감.
    // password는 알아서 처리함
    // username이 DB에 있는지 확인만 해주면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User principal = userRepository.findByUsername(username)
               .orElseThrow(()->{
                   return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."+username);
               });

        return new PrincipalDetail(principal); // 시큐리티 세션에 유저정보가 저장 id-user, password-console
    }
}
