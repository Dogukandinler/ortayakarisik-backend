package com.Trakya.OrtayaKarisik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "yorum")

public class Yorum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kullanici_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    String YorumIcerigi;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "restoran_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Restoran restoran;

    Short Puan;

    Date YorumTarihi;


}
