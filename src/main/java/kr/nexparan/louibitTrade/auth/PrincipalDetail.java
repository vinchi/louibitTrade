package kr.nexparan.louibitTrade.auth;

import kr.nexparan.louibitTrade.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
//UserDetails타입의 오브젝트를 스프링 시큐리티의 고유한 세션저장소에 저장해준다.
public class PrincipalDetail implements UserDetails {

    private User user; //콤포지션

    public PrincipalDetail(User user) {
        this.user = user;
    }

    //계정의 권한을 리턴해준다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "ROLE_" + "USER";
        });
        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았는지 리턴한다.(true:만료됨)
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    //계정이 잠겨있지 않은지 리턴한다.(true:잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    //비밀번호가 만료되지 않았는지 리턴한다.(true:만료됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    //계정이 활성화(사용가능)인지 리턴한다.(true:활성화)_
    @Override
    public boolean isEnabled() {
        return true;
    }
}
