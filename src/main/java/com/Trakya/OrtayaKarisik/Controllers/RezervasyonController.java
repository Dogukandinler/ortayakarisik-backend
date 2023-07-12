package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.model.Rezervasyon;
import com.Trakya.OrtayaKarisik.Requests.RezervationCreateRequest;
import com.Trakya.OrtayaKarisik.Requests.RezervationUpdateRequest;
import com.Trakya.OrtayaKarisik.Services.RezervasyonService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Rezervasyon")
public class RezervasyonController {

    RezervasyonService rezervasyonService;

    public RezervasyonController(RezervasyonService rezervasyonService) {
        this.rezervasyonService = rezervasyonService;
    }

    @GetMapping
    public List<Rezervasyon> getAllRezervation(@RequestParam Optional<Long> kullaniciId){
        return rezervasyonService.getAllRezervationBykullaniciId(kullaniciId);
    }

    @GetMapping("/Restoran")
    public List<Rezervasyon> getAllRezervationBy(@RequestParam Optional<Long> restoranId){
        return rezervasyonService.getAllRezervationByrestoranId(restoranId);
    }

    @PostMapping
    public Rezervasyon createOneRezervation(@RequestBody RezervationCreateRequest newRezervationRequest){
        return rezervasyonService.createOneRezervation(newRezervationRequest);
    }

    @PutMapping("/{RezervasyonId}")
    public Rezervasyon updateOneComment(@PathVariable Long RezervasyonId, @RequestBody RezervationUpdateRequest updateRezervation){
        return rezervasyonService.updateOneRezervationById(RezervasyonId,updateRezervation);
    }
}

