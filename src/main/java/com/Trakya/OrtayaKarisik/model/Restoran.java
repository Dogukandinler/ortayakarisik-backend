package com.Trakya.OrtayaKarisik.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "restorans")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restoran {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long Id;

  String RestoranAdi;

  int RestoranNumarasi;

  int RestoranKapasitesi;

  int RestoranPuanı;

  String ResotranEcte;

  String restoranEmail;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "konum_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  Konum konum;

  @JsonIgnoreProperties("restoran")
  @OneToMany(mappedBy = "restoran", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Menu> menus;

  String RestoranKısaAcıklama;

  String RestoranDetayliAciklama;

  @Transient public boolean isFavorite;

  private  String imgUrl;
}
