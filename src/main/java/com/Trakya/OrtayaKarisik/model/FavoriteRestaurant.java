package com.Trakya.OrtayaKarisik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "favorite_restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteRestaurant extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "restoran_id", nullable = false)
  private Restoran restoran;
}
