package com.cos.blog_project.service;

import com.cos.blog_project.model.Board;
import com.cos.blog_project.model.Reply;
import com.cos.blog_project.model.RoleType;
import com.cos.blog_project.model.User;
import com.cos.blog_project.repository.BoardRepository;
import com.cos.blog_project.repository.ReplyRepository;
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

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board, User user) {

        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다");
        });
    }

    @Transactional
    public void 글삭제하기(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다");
                }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수 종료 시(Service가 종료될 때) 트랜잭션이 종료 -> 더티체킹 -> 자동 업데이트가 됨(DB flush)
    }

    @Transactional
    public void 댓글쓰기(User user, int boardId, Reply requestReply){

       Board board = boardRepository.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 아이디를 찾을 수 없습니다");
        }); // 영속화 완료

        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);
    }

    @Transactional
    public void 댓글삭제(int replyId){
        replyRepository.deleteById(replyId);
    }


}
