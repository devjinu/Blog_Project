package com.cos.blog_project.config.auth;

import com.cos.blog_project.model.User;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면, UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해둔다.
@Getter
public class PrincipalDetail implements UserDetails {
    private User user; // 객체를 품고있음 -> 컴포지션

    public PrincipalDetail(User user){
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정의 만료 여부(true: 만료x)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정의 잠금 여부(true: 잠금x)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호의 만료 여부(true: 만료x)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부(true: 활성)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정의 권한 목록 여부(여러개인 경우 for)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collertors = new ArrayList<>();
        collertors.add(()-> {
            return "ROLE_"+user.getRole();
        });
        return collertors;
    }
}
