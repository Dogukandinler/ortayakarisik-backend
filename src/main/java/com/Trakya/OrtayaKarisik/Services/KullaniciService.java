package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.model.User;
import com.Trakya.OrtayaKarisik.Repository.KullaniciRepository;
import com.Trakya.OrtayaKarisik.dto.UserDto;
import com.Trakya.OrtayaKarisik.mapper.UserMapper;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;


    public List<UserDto> getAllUsers() {
        return kullaniciRepository.findAll()
                .stream().map(UserMapper.INSTANCE::toDto).toList();
    }

    public User createUser(User newUser) {
        return kullaniciRepository.save(newUser);
    }

    public User getOneUser(Long kullaniciId) {
        return kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public User updateOneUser(long kullaniciId, User newUser) {
        Optional<User> kullanici = kullaniciRepository.findById(kullaniciId);
        if (kullanici.isPresent()) {
            User foundUser = kullanici.get();
            foundUser.setId(newUser.getId());
            foundUser.setEmail(newUser.getEmail());
            foundUser.setTelNo(newUser.getTelNo());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setUserName(newUser.getUserName());
            kullaniciRepository.save(foundUser);
            return foundUser;
        } else return null;
    }

    public User saveOneUser(User newUser) {
        return kullaniciRepository.save(newUser);
    }

    public void deleteOneUser(Long kullaniciId) {
        kullaniciRepository.deleteById(kullaniciId);
    }

    public boolean userExistsByUsername(String username) {
        return kullaniciRepository.findByUserName(username).isPresent();
    }

    public User getOneUserByUserName(String username) {
        return kullaniciRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }
}
