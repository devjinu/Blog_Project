package com.cos.blog_project.controller.api;

import com.cos.blog_project.config.auth.PrincipalDetail;
import com.cos.blog_project.dto.ResponseDto;
import com.cos.blog_project.model.Board;
import com.cos.blog_project.model.User;
import com.cos.blog_project.service.BoardService;
import com.cos.blog_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){

        boardService.글쓰기(board, principal.getUser());
        System.out.println("BoardApiController : save 호출");
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
