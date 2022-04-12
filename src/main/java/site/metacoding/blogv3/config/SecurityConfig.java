package site.metacoding.blogv3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 걸어줘야 해당 파일로 시큐리티가 활성화 된다.
@Configuration // Ioc에 등록 , 등록이 됐다고 해서 시큐리티가 작동하는것은 아니다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // Ioc 컨테이너에 등록된다.
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    // 인증 설정하는 메서드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        http.csrf().disable(); // 이거 안하면 postman테스트 못함.
        http.authorizeRequests()
                .antMatchers("/s/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                // .usernameParameter("user")
                // .passwordParameter("pwd")
                .loginPage("/login-form")
                .loginProcessingUrl("/login") // login 프로세스를 탄다.
                .defaultSuccessUrl("/");
    }

}
