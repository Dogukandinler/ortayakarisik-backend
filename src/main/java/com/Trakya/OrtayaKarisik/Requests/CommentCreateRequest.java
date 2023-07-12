package com.Trakya.OrtayaKarisik.Requests;


import java.util.Date;
import lombok.Data;

@Data
public class CommentCreateRequest {
    Long Id;

    Long kullaniciId;

    Long restoranId;

    String yorumIcerigi;

    Short puan;

    Date YorumTarihi;

}
