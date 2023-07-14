package com.Trakya.OrtayaKarisik.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "kullanici")
@Data
public class User extends BaseEntity{

    @Column(unique = true)
    private  String userName;

    private  String password;

    private String email;

    private  String telNo;

    private  String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "konum_id")
    Konum konum;



}

