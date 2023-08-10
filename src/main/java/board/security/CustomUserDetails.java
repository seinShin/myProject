package board.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}
	
	
	// role이 단일 문자열일 경우 SimpleGrantedAuthority 객체를 생성하여 리스트에 추가
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;	// 사용자 계정이 만료되지 않음을 반환
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;	// 사용자 계정이 잠겨있지 않음을 반환
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;	// 사용자 자격 증명이 만료되지 않음을 반환
    }

    @Override
    public boolean isEnabled() {
        return true;	// 사용자 계정이 활성화됨을 반환
    }
}
