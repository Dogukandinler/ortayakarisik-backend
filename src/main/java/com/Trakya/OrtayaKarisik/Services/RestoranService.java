package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.model.Restoran;
import com.Trakya.OrtayaKarisik.Repository.RestoranRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RestoranService {
    RestoranRepository restoranRepository;

    public RestoranService(RestoranRepository restoranRepository) {
        this.restoranRepository = restoranRepository;
    }


    public List<Restoran> getAllRestoran() {
        return restoranRepository.findAll();
    }

    public Restoran getOneRestoran(Long restoranId) {
        return restoranRepository.findById(restoranId).orElse(null);
    }
}
