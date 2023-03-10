package com.DanCreate.securityTesting.service;

import com.DanCreate.securityTesting.model.dtos.AuthenticateDtos;
import com.DanCreate.securityTesting.model.dtos.RegisterDtos;
import com.DanCreate.securityTesting.model.entities.Role;
import com.DanCreate.securityTesting.model.entities.Token;
import com.DanCreate.securityTesting.model.entities.TokenType;
import com.DanCreate.securityTesting.reponsitory.TokenRepository;
import com.DanCreate.securityTesting.model.entities.User;
import com.DanCreate.securityTesting.reponsitory.UserRepository;
import com.DanCreate.securityTesting.response.AuthTokenResponse;
import com.DanCreate.securityTesting.util.JwtServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final UserRepository userRepository;
    private  final TokenRepository tokenRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtServiceUtil jwtServiceUtil;
    private  final AuthenticationManager authenticationManager;

    public AuthTokenResponse register(RegisterDtos registerRequest) {
        /*
        *dung var đe khai báo bien, viec chi định kieu du lieu của bien se đuoc rut gon,
        * và thay vao đo thi chuong trinh se tu đong chon kieu phu hop cho bien.
        * Và kieu du lieu của bien đuoc chuong trinh tu đong lua chon nay đuoc goi la
        * kieu suy luan trong Java.
        *
        * phan con lai kha de, luoi giai thich, thich thi tu tim lai nhe huong cua tuong lai =)) neu doc lai code
        */
        var user = User
                .builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        var getAndSaveUser = userRepository.save(user);
        var jwtToken = jwtServiceUtil.generateToken(user);
        saveUserToken(getAndSaveUser,jwtToken);
        return AuthTokenResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthTokenResponse authenticateAccount(AuthenticateDtos authRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
            authRequest.getEmail(),
            authRequest.getPassword()
            )
        );// xu ly xac nhan email va password duoc lay ra tu AuthenticationRequest request
        var user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow();//nem ra bat ki 1 exception nao
        var jwtToken = jwtServiceUtil.generateToken(user);
        revokeAllUserToken(user);//thu hoi jwt token cua doi tuong
        saveUserToken(user,jwtToken);//setting va dong trang thai
        return AuthTokenResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    private void  saveUserToken(User user, String jwtToken){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEADER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserToken(User user){
        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getId());
        if(validUserToken.isEmpty()) return;
        validUserToken.forEach(token -> { token.setExpired(true);
                                        token.setRevoked(true);

        });
        tokenRepository.saveAll(validUserToken);

    }
}
