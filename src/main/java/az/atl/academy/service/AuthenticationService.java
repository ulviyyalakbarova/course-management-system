package az.atl.academy.service;

import az.atl.academy.model.dto.JwtAuthenticationResponse;
import az.atl.academy.model.dto.SignInRequest;
import az.atl.academy.model.dto.SignUpRequest;
import az.atl.academy.model.entity.UserEntity;
import az.atl.academy.model.mapper.RoleMapper;
import az.atl.academy.repository.RoleRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record AuthenticationService(UserService userService,
                                    JwtService jwtService,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager,

                                    RoleRepository roleRepository
) {

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleMapper.INSTANCE.dtoToEntity(request.getRole()))
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
