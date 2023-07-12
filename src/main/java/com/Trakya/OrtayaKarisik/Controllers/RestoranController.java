package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.model.Restoran;
import com.Trakya.OrtayaKarisik.Services.RestoranService;
import java.util.List;

import com.Trakya.OrtayaKarisik.responses.RestoranResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restoran")
public class RestoranController {

    private RestoranService restoranService;

    public RestoranController(RestoranService restoranService){
        this.restoranService= restoranService;
    }

    @GetMapping
    public List<Restoran> getAllRestoran(){
        return restoranService.getAllRestoran();

    }
    @GetMapping("/{restoranID}")
    public Restoran getOneRestoran(@PathVariable Long restoranID){
        return restoranService.getOneRestoran(restoranID);

    }
}
