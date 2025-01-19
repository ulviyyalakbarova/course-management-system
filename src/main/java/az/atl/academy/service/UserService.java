package az.atl.academy.service;

import az.atl.academy.exception.AlreadyExistsException;
import az.atl.academy.model.dto.UserLightDto;
import az.atl.academy.model.entity.UserEntity;
import az.atl.academy.model.mapper.UserMapper;
import az.atl.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(UserEntity user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new AlreadyExistsException("A user with the same name already exists");
        }

        if(userRepository.existsByEmail(user.getEmail())){
            throw new AlreadyExistsException("A user with this email already exists");
        }

        userRepository.save(user);
    }

    public UserEntity getByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDetailsService userDetailsService(){
        return this::getByUsername;
    }

    public UserLightDto getUserDetailsByUsername(String username){
        var data = userRepository.findUserDetailsByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserMapper.INSTANCE.toLightDto(data);
    }
}
