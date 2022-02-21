package com.cos.blog_project.service;

import com.cos.blog_project.model.RoleType;
import com.cos.blog_project.model.User;
import com.cos.blog_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 -> IoC를 해줌
// 서비스 -> 1. 트랜잭션 관리
//       -> 2. 서비스 의미
@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user) {

        String rawPassword = user.getPassword(); // 원문
        String encPassword = encoder.encode(rawPassword); // 암호화
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

/*    @Transactional(readOnly = true) // select할 때 트랜잭션 시작, 서비스 종료 시 트랜잭션 종료
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/

    @Transactional
    public void 회원수정(User user){
        // 수정 시 JPA 영속성 컨텍스트에 User Object를 영속화 시키고, 영속화된 User Object를 수정
        // Select를 해서 User Object를 DB로 부터 가져오는 이유는 영속화를 하기 위해서
        // 영속화된 Object를 변경하면 자동으로 DB에 update
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
           return new IllegalArgumentException("회원 찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
        // 수정 함수 종료 = 서비스 종료 = 트랜잭션 종료 -> 커밋
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹 -> update

    }

}
