package com.Trakya.OrtayaKarisik.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "yemekler")
public class Yemek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //MenüEntity ile bi-directional ilişki
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private  Long fiyatı;

    private  String imgUrl;


    // Getter ve Setter metotları

    // Diğer alanlar ve metotlar
}
