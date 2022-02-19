package com.cos.blog_project.service;

import com.cos.blog_project.model.User;
import com.cos.blog_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 -> IoC를 해줌
// 서비스 -> 1. 트랜잭션 관리
//       -> 2. 서비스 의미
@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true) // select할 때 트랜잭션 시작, 서비스 종료 시 트랜잭션 종료
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
