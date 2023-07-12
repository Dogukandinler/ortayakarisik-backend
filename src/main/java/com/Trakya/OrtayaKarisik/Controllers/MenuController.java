package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.Services.MenuService;
import com.Trakya.OrtayaKarisik.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Menuler")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuServis) {
        this.menuService = menuServis;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("id") Long id) {
        Menu menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    // Diğer controller metotları
}

