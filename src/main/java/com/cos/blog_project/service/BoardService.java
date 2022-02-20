package com.cos.blog_project.service;

import com.cos.blog_project.model.Board;
import com.cos.blog_project.model.RoleType;
import com.cos.blog_project.model.User;
import com.cos.blog_project.repository.BoardRepository;
import com.cos.blog_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 -> IoC를 해줌
// 서비스 -> 1. 트랜잭션 관리
//       -> 2. 서비스 의미
@Service
public class BoardService {

    @Autowired
    public BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) {

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

}
