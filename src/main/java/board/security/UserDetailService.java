package board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import board.security.mapper.userMapper;

@Service
public class UserDetailService implements UserDetailsService {
	
	 @Autowired
	 private userMapper userMapper;

	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getAuthInfo(username);
      
        if (user == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        
        user.setDetail(user);
        
        return new CustomUserDetails(user);
    }
}
