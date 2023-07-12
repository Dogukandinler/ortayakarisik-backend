package com.Trakya.OrtayaKarisik.Repository;

import com.Trakya.OrtayaKarisik.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciRepository extends JpaRepository<User,Long>{
    Optional<User> findByUserName(String userName);
}
