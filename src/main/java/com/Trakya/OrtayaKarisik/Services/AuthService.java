package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.model.User;
import com.Trakya.OrtayaKarisik.Requests.UserRequest;
import com.Trakya.OrtayaKarisik.Security.JwtTokenProvider;
import com.Trakya.OrtayaKarisik.responses.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final KullaniciService kullaniciService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = kullaniciService.getOneUserByUserName(loginRequest.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage(jwtTokenProvider.generateToken(auth));
        authResponse.setUserId(user.getId());
        return authResponse;
    }

    public User register(@Valid @RequestBody UserRequest registerRequest) {
        if (kullaniciService.userExistsByUsername(registerRequest.getUserName())) {
            throw new RuntimeException("Username Already Use");
        }
        User user = new User();
        BeanUtils.copyProperties(registerRequest, user);

        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return kullaniciService.saveOneUser(user);
    }
}
