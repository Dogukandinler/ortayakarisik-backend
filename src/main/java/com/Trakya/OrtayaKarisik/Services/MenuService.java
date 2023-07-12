package com.Trakya.OrtayaKarisik.Services;

import com.Trakya.OrtayaKarisik.Repository.MenuRepository;
import com.Trakya.OrtayaKarisik.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
    }

    // Diğer servis metotları
}
