package com.cos.blog_project.test;

import lombok.*;

/*@Getter
@Setter*/

@Data // -> getter and setter
// @AllArgsConstructor  // -> constructor
// @RequiredArgsConstructor // final 붙은 애들의 constructor 만들어줌
@NoArgsConstructor // -> 빈 생성자
public class Member {

    private int id;
    private String username;
    private String password;
    private String email;

    // 생성자의 순서를 지키지 않아도 되고, 필드의 값을 신경쓰지 않아도 됨
    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
}
