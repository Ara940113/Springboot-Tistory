package site.metacoding.blogv3.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.User;
import site.metacoding.blogv3.domain.user.UserRepository;

@RequiredArgsConstructor
@Service // Ioc 등록
public class UserService {

    // DI
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCtyptPasswordEncoder;

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 1234 1234로 회원가입을 할 수는 없지만 시큐리티때문에 1234로 로그인은 안된다.
        String encPassword = bCtyptPasswordEncoder.encode(rawPassword); // 해쉬 알고리즘을 통해 패스워드를 인코딩
        user.setPassword(encPassword);
        userRepository.save(user);
    }

    @Transactional
    public String 유저네임중복검사(String username) {
        User userEntity = userRepository.mUsernameSameCheck(username);

        if (userEntity == null) {
            return "없어";
        } else {
            return "있어";
        }
    }
}
