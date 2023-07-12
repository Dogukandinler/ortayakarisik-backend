package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.Services.MenuService;
import com.Trakya.OrtayaKarisik.Services.YemekService;
import com.Trakya.OrtayaKarisik.model.Menu;
import com.Trakya.OrtayaKarisik.model.Yemek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Yemekler")
public class YemekController  {

    private final YemekService yemekService;

    @Autowired
    public YemekController(YemekService yemekServis) {
        this.yemekService = yemekServis;
    }

    @PostMapping
    public ResponseEntity<Yemek> createYemek(@RequestBody Yemek yemek) {
        Yemek createdYemek = yemekService.createYemek(yemek);
        return ResponseEntity.ok(createdYemek);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Yemek> getYemekById(@PathVariable("id") Long id) {
        Yemek yemek = yemekService.getYemekById(id);
        return ResponseEntity.ok(yemek);
    }

    // Diğer controller metotları
}
