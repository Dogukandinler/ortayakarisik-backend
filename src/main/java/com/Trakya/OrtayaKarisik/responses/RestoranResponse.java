package com.Trakya.OrtayaKarisik.responses;

import com.Trakya.OrtayaKarisik.model.Konum;
import com.Trakya.OrtayaKarisik.model.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
public class RestoranResponse {

    Long Id;

    String RestoranAdi;

    int RestoranNumarasi;

    int RestoranKapasitesi;

    int RestoranPuanı;

    String ResotranEcte;

    Konum konum;

    String RestoranKısaAcıklama;

    String RestoranDetayliAciklama;




}
