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

        /*@ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "konum_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        Konum konum;*/


}

