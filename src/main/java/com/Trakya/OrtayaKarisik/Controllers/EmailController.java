package com.Trakya.OrtayaKarisik.Controllers;

import com.Trakya.OrtayaKarisik.Services.EmailService;
import com.Trakya.OrtayaKarisik.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  @Autowired private EmailService emailService;

  @PostMapping("/sendMail")
  public String sendMail(@RequestBody EmailDetails details) {
    return emailService.sendSimpleMail(details);
  }

  @PostMapping("/sendMailWithAttachment")
  public String sendMailWithAttachment(@RequestBody EmailDetails details) {
    return emailService.sendMailWithAttachment(details);
  }
}
