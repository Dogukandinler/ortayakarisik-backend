package com.Trakya.OrtayaKarisik.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@Entity
@Table(name = "restorans")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restoran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String RestoranAdi;

    int RestoranNumarasi;

    int RestoranKapasitesi;

    int RestoranPuanı;

    String ResotranEcte;


    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "konum_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Konum konum;

    @JsonIgnoreProperties("restoran")
    @OneToMany(mappedBy = "restoran", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Menu> menus;

    String RestoranKısaAcıklama;

    String RestoranDetayliAciklama;








}
