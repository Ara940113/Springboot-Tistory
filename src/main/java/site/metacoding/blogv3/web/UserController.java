package site.metacoding.blogv3.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.UserRepository;
import site.metacoding.blogv3.handler.ex.CustomException;
import site.metacoding.blogv3.service.UserService;
import site.metacoding.blogv3.web.dto.user.JoinReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    // DI
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping({ "/login-form" })
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping({ "/join-form" })
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("/api/user/username/same-check") // 체크하는 동사는 안적는게 좋지만 어쩔수없이 적자! 확인은 해야하니까
    public ResponseEntity<?> usernameSameCheck(String username) {
        boolean isNotSame = userService.유저네임중복체크(username); // true 같지 않다
        return new ResponseEntity<>(isNotSame, HttpStatus.OK);

    }

    @PostMapping({ "/join" })
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) { // valid를 붙이면 제약조건을 체크한다.
        // 디스패쳐서블릿이 컨트롤러의 조인을 호출할 때 벨리데이션을 체크한다.
        // 체킹한 결과값은 BindingResult 에 담긴다.
        // User를 쓰면 벨리데이션을 할 수가 없다. 회원가입을 할 때는 아이디와 비밀번호, 이메일 세개가 필요한데 로그인 할때는 두개만 있으면
        // 된다.
        // 그래서 Dto가 필요하다!!
        // BindingResult는 무조건 내가 쓸 Dto 뒤에 와야한다!!

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                // System.out.println(fe.getField());
                // System.out.println(fe.getDefaultMessage());
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            } // 핵심로직이 아닌 부가적인 코드 (AOP처리 가능 - 공통로직 처리)
            throw new CustomException(errorMap.toString());
        }

        // 핵심로직!! 회원가입을 하는 것@@!!!
        userService.회원가입(joinReqDto.toEntity());

        return "redirect:/login-form";
    }

}