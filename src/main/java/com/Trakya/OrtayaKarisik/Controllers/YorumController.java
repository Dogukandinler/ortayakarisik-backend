package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.model.Yorum;
import com.Trakya.OrtayaKarisik.Requests.CommentCreateRequest;
import com.Trakya.OrtayaKarisik.Requests.CommentUpdateRequest;
import com.Trakya.OrtayaKarisik.Services.YorumService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Yorumlar")
public class YorumController {

    YorumService yorumService;

    public YorumController(YorumService yorumService) {
        this.yorumService = yorumService;
    }

    @GetMapping
    public List<Yorum> getAllComments(@RequestParam Optional<Long> kullaniciId){
        return yorumService.getAllCommentsBykullaniciId(kullaniciId);
    }
    @GetMapping("/RestoranId")
    public List<Yorum> getAllCommentsBy(@RequestParam Optional<Long> restoranId){
        return yorumService.getAllCommentsByrestoranId(restoranId);
    }

    @PostMapping
    public Yorum createOneComment(@RequestBody CommentCreateRequest newCommentRequest){
        return yorumService.createOneComment(newCommentRequest);
    }
    @PutMapping("/{yorumId}")
    public Yorum updateOneComment(@PathVariable Long yorumId, @RequestBody CommentUpdateRequest updateComment){
        return yorumService.updateOneCommentById(yorumId,updateComment);
    }
    @DeleteMapping("/{yorumId}")
    public void deleteOneComment(@PathVariable Long yorumId){
         YorumService.deleteOneCommentById(yorumId);
    }
}
