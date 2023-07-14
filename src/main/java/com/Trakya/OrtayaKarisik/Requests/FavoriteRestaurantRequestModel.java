package com.Trakya.OrtayaKarisik.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteRestaurantRequestModel {

  private Long id;
  private Long kullaniciId;
  private Long restoranId;
}
