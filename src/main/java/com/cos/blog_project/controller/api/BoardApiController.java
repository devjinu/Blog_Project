package com.cos.blog_project.controller.api;

import com.cos.blog_project.config.auth.PrincipalDetail;
import com.cos.blog_project.dto.ResponseDto;
import com.cos.blog_project.model.Board;
import com.cos.blog_project.model.Reply;
import com.cos.blog_project.model.User;
import com.cos.blog_project.service.BoardService;
import com.cos.blog_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
        boardService.글수정하기(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){

        boardService.댓글쓰기(principal.getUser(), boardId, reply);
        System.out.println("BoardApiController : save 호출");
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
