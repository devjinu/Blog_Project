package com.cos.blog_project.controller.api;

import com.cos.blog_project.dto.ResponseDto;
import com.cos.blog_project.model.RoleType;
import com.cos.blog_project.model.User;
import com.cos.blog_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){

        System.out.println("UserApiController : save 호출");

        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /*
    전통적인 로그인 방식
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController : login 호출");
        User principal = userService.로그인(user); // principal -> 접근주체

        if(principal != null){
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    */

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        userService.회원수정(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
