package com.cos.blog_project.test;

import com.cos.blog_project.model.RoleType;
import com.cos.blog_project.model.User;
import com.cos.blog_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    // http://localhost:8000/blog/dummy/join(요청)
    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user){

        System.out.println("id : "+user.getId());
        System.out.println("username : "+ user.getUsername());
        System.out.println("password : "+ user.getPassword());
        System.out.println("email : "+ user.getEmail());
        System.out.println("role : "+user.getRole());
        System.out.println("createDate : "+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다";
    }

}
