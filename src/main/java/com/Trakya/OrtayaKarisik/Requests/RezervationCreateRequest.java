package com.Trakya.OrtayaKarisik.Requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class RezervationCreateRequest {

    Long kullaniciId;

    Long restoranId;

    Long KisiSayisi;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     LocalDate gun;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
     LocalTime saat;

}
