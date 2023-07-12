package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.Repository.YemekRepository;
import com.Trakya.OrtayaKarisik.model.Yemek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YemekService {

    private final YemekRepository yemekRepository;

    @Autowired
    public YemekService(YemekRepository yemekRepository) {
        this.yemekRepository = yemekRepository;
    }

    public Yemek createYemek(Yemek yemek) {
        return yemekRepository.save(yemek);
    }

    public Yemek getYemekById(Long id) {
        return yemekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yemek not found with id: " + id));
    }

    // Diğer servis metotları
}