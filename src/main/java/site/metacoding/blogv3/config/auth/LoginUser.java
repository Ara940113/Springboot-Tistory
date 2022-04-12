package site.metacoding.blogv3.config.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.User;

@Data
@RequiredArgsConstructor
public class LoginUser implements UserDetails {

    private final User user;

    // 궁금하면 졸업하고 취업전에 유튜브 시큐리티
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권한
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 하나라도 false 걸려있으면 터진다!!!!!

    @Override
    public boolean isAccountNonExpired() {
        return true; // 만기됐어여? 아니요
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 락 안걸렸죠? 네
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만기됐어요? 아니요
    }

    @Override
    public boolean isEnabled() {
        return true; //
    }

}
