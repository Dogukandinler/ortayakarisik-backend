package com.Trakya.OrtayaKarisik.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "konum")
@Data
public class Konum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long KonumId;

    private   String Il;

    private   String Ilce;

    private    String  Mahelle;

    private  String Sokak;

    private   Short BinaNo;

    private   Short KapıNo;



}
