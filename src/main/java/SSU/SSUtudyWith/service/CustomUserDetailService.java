package SSU.SSUtudyWith.service;

import SSU.SSUtudyWith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String Id){

        return  userRepository.findById(Long.parseLong(Id))
                .orElseThrow(() -> new EntityNotFoundException("해당하는 유저가 없습니다."));
    }
}


