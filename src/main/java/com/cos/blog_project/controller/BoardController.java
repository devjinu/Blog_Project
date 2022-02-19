package com.cos.blog_project.controller;

import com.cos.blog_project.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {

    @GetMapping({"","/"})
    public String index( @AuthenticationPrincipal PrincipalDetail principal){ // 컨트롤러에서 세션 찾는 법
        System.out.println("로그인 사용자 아이디 "+ principal.getUsername());
        return "index";
    }

}
