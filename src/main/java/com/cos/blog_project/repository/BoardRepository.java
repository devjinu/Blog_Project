package com.cos.blog_project.repository;

import com.cos.blog_project.model.Board;
import com.cos.blog_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Integer> {

}
