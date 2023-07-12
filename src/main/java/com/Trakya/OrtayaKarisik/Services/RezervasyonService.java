package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.model.Restoran;
import com.Trakya.OrtayaKarisik.model.Rezervasyon;
import com.Trakya.OrtayaKarisik.model.User;
import com.Trakya.OrtayaKarisik.Repository.RezervasyonRepository;
import com.Trakya.OrtayaKarisik.Requests.RezervationCreateRequest;
import com.Trakya.OrtayaKarisik.Requests.RezervationUpdateRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RezervasyonService {
    static RezervasyonRepository rezervasyonRepository;
    private KullaniciService kullaniciService;
    private RestoranService restoranService;

    public RezervasyonService(RezervasyonRepository rezervasyonRepository, KullaniciService kullaniciService, RestoranService restoranService) {
        this.rezervasyonRepository = rezervasyonRepository;
        this.kullaniciService = kullaniciService;
        this.restoranService = restoranService;
    }

    public static List<Rezervasyon> getAllRezervationBykullaniciId(Optional<Long> kullaniciId) {
        if (kullaniciId.isPresent()) {
            return rezervasyonRepository.findAllByUser_Id(kullaniciId.get());
        }

        return rezervasyonRepository.findAll();
    }

    public static List<Rezervasyon> getAllRezervationByrestoranId(Optional<Long> restoranId) {
        if (restoranId.isPresent()) {
            return rezervasyonRepository.findAllById(restoranId.get());
        }

        return rezervasyonRepository.findAll();
    }

    public Rezervasyon createOneRezervation(RezervationCreateRequest newRezervationRequest) {
        User user = kullaniciService.getOneUser(newRezervationRequest.getKullaniciId());
        Restoran restoran = restoranService.getOneRestoran(newRezervationRequest.getRestoranId());
        if (user != null && restoran != null) {
            Rezervasyon toSave = new Rezervasyon();
            toSave.setId(newRezervationRequest.getId());
            toSave.setUser(user);
            toSave.setRestoran(restoran);
            toSave.setKisiSayisi(newRezervationRequest.getKisiSayisi());

            return rezervasyonRepository.save(toSave);
        } else
            return null;
    }

    public Rezervasyon updateOneRezervationById(Long rezervasyonId, RezervationUpdateRequest updateRezervation) {
        Optional<Rezervasyon> rezervasyon = rezervasyonRepository.findById(rezervasyonId);
        if (rezervasyon.isPresent()) {
            Rezervasyon toUpdate = rezervasyon.get();
            toUpdate.setKisiSayisi(updateRezervation.getKisisayisi());
            return rezervasyonRepository.save(toUpdate);

        } else
            return null;
    }

}
