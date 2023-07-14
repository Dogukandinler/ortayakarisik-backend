package com.Trakya.OrtayaKarisik.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "yorum")
public class Yorum  extends BaseEntity {


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kullanici_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  User user;

  String YorumIcerigi;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "restoran_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnoreProperties("menu")
  Restoran restoran;

  Short Puan;

}
