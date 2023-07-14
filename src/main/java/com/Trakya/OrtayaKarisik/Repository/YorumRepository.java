package com.Trakya.OrtayaKarisik.Repository;

import com.Trakya.OrtayaKarisik.model.Yorum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YorumRepository extends JpaRepository<Yorum,Long> {
    List<Yorum> findAllByUser_Id(Long kullaniciId);
    List<Yorum> findAllByRestoranId(Long restoranId);

    boolean existsYorumByRestoran_Id_AndUser_Id (Long restoranId, Long userId);
}
