package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.Repository.RestoranRepository;
import com.Trakya.OrtayaKarisik.model.Restoran;
import com.Trakya.OrtayaKarisik.model.User;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RestoranService {
  private final RestoranRepository restoranRepository;
  private final FavoriteRestaurantService favoriteRestaurantService;
  private final KullaniciService kullaniciService;

  public RestoranService(
      RestoranRepository restoranRepository,
      FavoriteRestaurantService favoriteRestaurantService,
      KullaniciService kullaniciService) {
    this.restoranRepository = restoranRepository;
    this.favoriteRestaurantService = favoriteRestaurantService;
    this.kullaniciService = kullaniciService;
  }

  public List<Restoran> getAllRestoran(String search) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    User user = kullaniciService.getOneUserByUserName(auth.getName());

    Set<Long> favoriteRestaurantIds =
        favoriteRestaurantService.getFavoriteRestaurantsByUser(user.getId()).stream()
            .map(Restoran::getId)
            .collect(Collectors.toSet());

    var restorants = restoranRepository.findAll();

    if (search != null && search.trim().length() > 0) {
      restorants =
          restorants.stream()
              .filter(
                  x ->
                      x.getResotranEcte().toLowerCase().contains(search.toLowerCase())
                          || x.getRestoranAdi().toLowerCase().contains(search.toLowerCase())
                          || x.getKonum().getIl().toLowerCase().contains(search.toLowerCase()))
              .toList();
    }

    restorants.forEach(x -> x.setFavorite(favoriteRestaurantIds.contains(x.getId())));

    return restorants;
  }

  public Restoran getOneRestoran(Long restoranId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = kullaniciService.getOneUserByUserName(auth.getName());
    Set<Long> favoriteRestaurantIds =
        favoriteRestaurantService.getFavoriteRestaurantsByUser(user.getId()).stream()
            .map(Restoran::getId)
            .collect(Collectors.toSet());

    var restorant = restoranRepository.findById(restoranId).orElse(null);

    if (favoriteRestaurantIds.contains(restorant.getId())) {
      restorant.setFavorite(true);
    }

    return restorant;
  }
}
