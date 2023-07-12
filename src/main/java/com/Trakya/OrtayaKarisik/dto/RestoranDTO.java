package com.Trakya.OrtayaKarisik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestoranDTO {

  private Long id;

  private String restoranAdi;

  private int restoranNumarasi;

  private int restoranKapasitesi;

  private int restoranPuanı;

  private String resotranEcte;

  private String restoranKısaAcıklama;

  private String restoranDetayliAciklama;
}
