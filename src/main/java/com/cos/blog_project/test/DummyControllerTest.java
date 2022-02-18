package com.cos.blog_project.test;

import com.cos.blog_project.model.RoleType;
import com.cos.blog_project.model.User;
import com.cos.blog_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터를 리턴 (페이징처리)
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
         Page<User> pagingUser = userRepository.findAll(pageable);
         List<User> users = pagingUser.getContent();
         return users;
    }



    // {id} 주소로 파라미터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // DB에서 null인  user를 찾는 경우 -> return null
        // optional로 user객체를 감싸 null인지 아닌지 판단해서 return

/*        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
            }
        });
        return user;
    }*/
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. id : "+id));

        // user 객체 -> 자바 오브젝트, 요청 -> 웹브라우저
        // user 객체를 json(웹 브라우저가 이해할 수 있는 데이터)형태로 변환
        // 스프링부트 => MessageConverter가 Jackson 라이브러리를 호출해 응답 시 자동 작동 -> json타입으로 변환해서 리턴
    return user;
    }

    // http://localhost:8000/blog/dummy/join(요청)
    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user){

        System.out.println("id : "+user.getId());
        System.out.println("username : "+ user.getUsername());
        System.out.println("password : "+ user.getPassword());
        System.out.println("email : "+ user.getEmail());
        System.out.println("role : "+user.getRole());
        System.out.println("createDate : "+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }

}
