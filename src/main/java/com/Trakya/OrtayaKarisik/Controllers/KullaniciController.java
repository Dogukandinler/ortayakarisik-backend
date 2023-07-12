package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.model.User;
import com.Trakya.OrtayaKarisik.Services.KullaniciService;
import com.Trakya.OrtayaKarisik.dto.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kullanici")
@RequiredArgsConstructor
public class KullaniciController {


    private final KullaniciService kullaniciService;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return kullaniciService.getAllUsers();

    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return  kullaniciService.createUser(newUser);
    }

    @GetMapping("/{kullaniciID}")
    public User getOneUser(@PathVariable Long kullaniciID){
        return kullaniciService.getOneUser(kullaniciID);

    }

    @PutMapping("/{kullaniciID}")
    public User updateOneUser(@PathVariable long kullaniciID, @RequestBody User newUser){
        return kullaniciService.updateOneUser(kullaniciID,newUser);

        }
    @DeleteMapping("/{kullaniciID}")
    public void deleteOneUser(@PathVariable Long kullaniciID){
        kullaniciService.deleteOneUser(kullaniciID);


    }
}
