package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.Requests.FavoriteRestaurantRequestModel;
import com.Trakya.OrtayaKarisik.Services.FavoriteRestaurantService;
import com.Trakya.OrtayaKarisik.model.Restoran;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteRestaurantController {

  private final FavoriteRestaurantService favoriteRestaurantService;

  @PostMapping
  ResponseEntity<Object> addFavoriteRestaurant(
      @RequestBody FavoriteRestaurantRequestModel requestModel) {
    favoriteRestaurantService.addToFavorite(requestModel);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  ResponseEntity<Object> removeFavoriteRestaurant(
      @RequestBody FavoriteRestaurantRequestModel requestModel) {
    favoriteRestaurantService.removeFromFavorite(requestModel);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/user/{id}")
  ResponseEntity<List<Restoran>> getFavoriteRestaurantsByUser(@PathVariable Long id) {
    return ResponseEntity.ok().body(favoriteRestaurantService.getFavoriteRestaurantsByUser(id));
  }
}
