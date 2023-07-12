package com.Trakya.OrtayaKarisik.Repository;

import com.Trakya.OrtayaKarisik.model.FavoriteRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurant, Long> {

    Optional<FavoriteRestaurant> findByRestoran_IdAndUser_Id(
            Long restaurantId, Long userId);


    List<FavoriteRestaurant> findAllByUser_Id(Long userId);
}