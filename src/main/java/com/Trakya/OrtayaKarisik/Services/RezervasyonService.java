package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.Repository.RezervasyonRepository;
import com.Trakya.OrtayaKarisik.Requests.RezervationCreateRequest;
import com.Trakya.OrtayaKarisik.Requests.RezervationUpdateRequest;
import com.Trakya.OrtayaKarisik.dto.EmailDetails;
import com.Trakya.OrtayaKarisik.model.Restoran;
import com.Trakya.OrtayaKarisik.model.Rezervasyon;
import com.Trakya.OrtayaKarisik.model.User;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RezervasyonService {
  static RezervasyonRepository rezervasyonRepository;
  private KullaniciService kullaniciService;
  private RestoranService restoranService;

  private EmailService emailService;

  public RezervasyonService(
      RezervasyonRepository rezervasyonRepository,
      KullaniciService kullaniciService,
      RestoranService restoranService,
      EmailService emailService) {
    this.rezervasyonRepository = rezervasyonRepository;
    this.kullaniciService = kullaniciService;
    this.restoranService = restoranService;
    this.emailService = emailService;
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
      toSave.setUser(user);
      toSave.setRestoran(restoran);
      toSave.setKisiSayisi(newRezervationRequest.getKisiSayisi());
      toSave.setSaat(newRezervationRequest.getSaat());
      toSave.setGun(newRezervationRequest.getGun());

      var kullanıcıMail = new EmailDetails();
      String msgBody =
          "Kullanıcı: "
              + user.getUserName()
              + "\nKişi Sayısı: "
              + toSave.getKisiSayisi()
              + "\nGün: "
              + newRezervationRequest.getGun().toString()
              + "\nSaat: "
              + newRezervationRequest.getSaat().toString();
        kullanıcıMail.setSubject("Rezervasyon  Yapıldı");
        kullanıcıMail.setRecipient("dogikan12@hotmail.com");
        kullanıcıMail.setMsgBody(msgBody);
        emailService.sendSimpleMail(kullanıcıMail);
      return rezervasyonRepository.save(toSave);
    } else return null;
  }

  public static void deleteOneRezervasyonById(Long RezervasyonId) {
    rezervasyonRepository.deleteById(RezervasyonId);
  }



  public Rezervasyon updateOneRezervationById(
      Long rezervasyonId, RezervationUpdateRequest updateRezervation) {
    Optional<Rezervasyon> rezervasyon = rezervasyonRepository.findById(rezervasyonId);
    if (rezervasyon.isPresent()) {
      Rezervasyon toUpdate = rezervasyon.get();
      toUpdate.setKisiSayisi(updateRezervation.getKisisayisi());
      return rezervasyonRepository.save(toUpdate);

    } else return null;
  }
}
