package com.Trakya.OrtayaKarisik.Repository;

import com.Trakya.OrtayaKarisik.model.Rezervasyon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervasyonRepository extends JpaRepository<Rezervasyon,Long> {
    List<Rezervasyon> findAllByUser_Id(Long kullaniciId);

    List<Rezervasyon> findAllById(Long restoranId);
    

}
