package com.Trakya.OrtayaKarisik.Requests;


import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentCreateRequest {
    @NotBlank
    Long kullaniciId;
    @NotBlank
    Long restoranId;
    @NotBlank
    String yorumIcerigi;
    @NotBlank
    Short puan;

    Date YorumTarihi;

}
