package com.Trakya.OrtayaKarisik.Requests;

import lombok.Data;

@Data
public class RezervationCreateRequest {
    Long Id;

    Long kullaniciId;

    Long restoranId;

    Long KisiSayisi;
}
