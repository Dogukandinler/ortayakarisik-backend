package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.Repository.FavoriteRestaurantRepository;
import com.Trakya.OrtayaKarisik.Requests.FavoriteRestaurantRequestModel;
import com.Trakya.OrtayaKarisik.model.FavoriteRestaurant;
import com.Trakya.OrtayaKarisik.model.Restoran;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FavoriteRestaurantService {

  private final FavoriteRestaurantRepository favoriteRestaurantRepository;
  private final KullaniciService userService;
  private final RestoranService restaurantService;

  public FavoriteRestaurantService(
      FavoriteRestaurantRepository favoriteRestaurantRepository,
      KullaniciService userService,
      @Lazy RestoranService restaurantService) {
    this.favoriteRestaurantRepository = favoriteRestaurantRepository;
    this.userService = userService;
    this.restaurantService = restaurantService;
  }

  public void addToFavorite(FavoriteRestaurantRequestModel requestModel) {
    var existing =
        favoriteRestaurantRepository.findByRestoran_IdAndUser_Id(
            requestModel.getRestaurantId(), requestModel.getUserId());
    var user = userService.getOneUser(requestModel.getUserId());
    var restaurant = restaurantService.getOneRestoran(requestModel.getRestaurantId());
    if (existing.isEmpty()) {
      favoriteRestaurantRepository.save(
          FavoriteRestaurant.builder().user(user).restoran(restaurant).build());
    }
  }

  public void removeFromFavorite(FavoriteRestaurantRequestModel requestModel) {
    var existing =
        favoriteRestaurantRepository.findByRestoran_IdAndUser_Id(
            requestModel.getRestaurantId(), requestModel.getUserId());
    existing.ifPresent(
        favoriteRestaurant -> favoriteRestaurantRepository.deleteById(favoriteRestaurant.getId()));
  }

  public List<Restoran> getFavoriteRestaurantsByUser(Long userId) {
    var result = favoriteRestaurantRepository.findAllByUser_Id(userId);
    return result.stream().map(FavoriteRestaurant::getRestoran).toList();
  }
}
