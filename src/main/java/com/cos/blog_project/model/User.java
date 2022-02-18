package com.cos.blog_project.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity // User클래스가 MySQL에 테이블이 생성이 된다
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 비밀번호 암호화
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; // Enum을 쓰는게 좋다 (admin, user, manager 등의 오타방지)

    @CreationTimestamp // 시간이 자동으로 입력됨
    private Timestamp createDate;

}