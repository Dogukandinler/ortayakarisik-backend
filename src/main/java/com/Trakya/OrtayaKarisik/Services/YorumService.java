package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.Repository.YorumRepository;
import com.Trakya.OrtayaKarisik.Requests.CommentCreateRequest;
import com.Trakya.OrtayaKarisik.Requests.CommentUpdateRequest;
import com.Trakya.OrtayaKarisik.model.Restoran;
import com.Trakya.OrtayaKarisik.model.User;
import com.Trakya.OrtayaKarisik.model.Yorum;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class YorumService {

  private static YorumRepository yorumRepository;
  private KullaniciService kullaniciService;
  private RestoranService restoranService;

  public YorumService(
      YorumRepository yorumRepository,
      KullaniciService kullaniciService,
      RestoranService restoranService) {
    this.yorumRepository = yorumRepository;
    this.kullaniciService = kullaniciService;
    this.restoranService = restoranService;
  }

  public static void deleteOneCommentById(Long yorumId) {
    yorumRepository.deleteById(yorumId);
  }

  public List<Yorum> getAllCommentsBykullaniciId(Optional<Long> kullaniciId) {
    if (kullaniciId.isPresent()) {
      var yorumlar = yorumRepository.findAllByUser_Id(kullaniciId.get());
      yorumlar.forEach(x -> x.getRestoran().setMenus(null));
      return yorumlar;
    }

    var yorumlar = yorumRepository.findAll();
    yorumlar.forEach(x -> x.getRestoran().setMenus(null));
    return yorumlar;
  }

  public List<Yorum> getAllCommentsByrestoranId(Optional<Long> restoranId) {
    if (restoranId.isPresent()) {

      var yorumlar = yorumRepository.findAllByRestoranId(restoranId.get());
      yorumlar.forEach(x -> x.getRestoran().setMenus(null));
      return yorumlar;
    }

    var yorumlar = yorumRepository.findAll();
    yorumlar.forEach(x -> x.getRestoran().setMenus(null));
    return yorumlar;
  }

  public Yorum createOneComment(CommentCreateRequest newCommentRequest) {
    User user = kullaniciService.getOneUser(newCommentRequest.getKullaniciId());
    Restoran restoran = restoranService.getOneRestoran(newCommentRequest.getRestoranId());

    if (user != null
        && restoran != null
        && !yorumRepository.existsYorumByRestoran_Id_AndUser_Id(restoran.getId(), user.getId())) {
      Yorum toSave = new Yorum();
      toSave.setPuan(newCommentRequest.getPuan());
      toSave.setUser(user);
      toSave.setRestoran(restoran);
      toSave.setYorumIcerigi(newCommentRequest.getYorumIcerigi());
      return yorumRepository.save(toSave);
    } else return null;
  }

  public Yorum updateOneCommentById(Long yorumId, CommentUpdateRequest updateComment) {
    Optional<Yorum> yorum = yorumRepository.findById(yorumId);
    if (yorum.isPresent()) {
      Yorum toUpdate = yorum.get();
      toUpdate.setYorumIcerigi(updateComment.getYorumIcerigi());
      toUpdate.setPuan(updateComment.getPuan());
      return yorumRepository.save(toUpdate);

    } else return null;
  }
}
