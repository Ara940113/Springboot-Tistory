package site.metacoding.blogv3.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.blogv3.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinReqDto {

    @Pattern(regexp = "[a-zA-Z1-9]{4,20}", message = "유저네임은 한글이 들어갈 수 없습니다.")
    @Size(min = 4, max = 20)
    @NotBlank
    private String username;

    @Size(min = 4, max = 20)
    @NotBlank
    private String password;

    @Size(min = 8, max = 60)
    @NotBlank // @NotNull, @NutEmpty 두개의 조합
    @Email // 이메일 형식이 아니면 걸러냄
    private String email;

    public User toEntity() {
        User user = new User();// null 없이 넣을것만 넣으면 된다.
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // User user = new User(null,username,password,email,null,null);
        // null은 내가 id를 Integer로 만들었기 때문에 가능 , id는 레퍼로 만든다!
        // 롬복이 풀 컨스트럭쳐는 만들어주지만 한개만 뺀건 만들어주지 않는다.
        // 컴파일시에 순서가 바뀐걸 잡을 수 없다 - 그래서 생성자를 만들지 않는다.

        // User user = User.builder() // 순서 상관없이 적을거만 적으면 된다!
        // .username(username)
        // .password(password)
        // .email(email)
        // .build();
        return user;
    }
}
