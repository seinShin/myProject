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
    public UserDetails loadUserByUsername(String insertedId) throws UsernameNotFoundException {
        User user = userMapper.getUserInfo(insertedId);
        
        if (user == null) {
            throw new UsernameNotFoundException("일치하는 아이디가 없습니다.");
        }
        
       
//        String pw = user.getPassword(); 
//        String role = user.getRole(); 
        System.out.println("user"+ user.getDetail());
        return new CustomUserDetails(user);
    }
}
