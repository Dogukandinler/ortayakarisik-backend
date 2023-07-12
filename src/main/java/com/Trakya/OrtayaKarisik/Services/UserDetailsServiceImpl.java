package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.model.User;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final KullaniciService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOneUserByUserName(username);
        var roles = Collections.emptyList().stream().map(x -> new SimpleGrantedAuthority(x.toString()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(), roles);
    }
}