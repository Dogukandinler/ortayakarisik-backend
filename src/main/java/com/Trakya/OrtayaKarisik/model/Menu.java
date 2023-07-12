package com.Trakya.OrtayaKarisik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name = "menus")
public class Menu  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    // Bir menüye ait birden çok yemek
    @JsonManagedReference
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Yemek> yemekler;

    // RestoranEntity ile bi-directional ilişki
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    // Getter ve Setter metotları

    // Diğer alanlar ve metotlar
}
