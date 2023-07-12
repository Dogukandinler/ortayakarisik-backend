package com.Trakya.OrtayaKarisik.dto;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
  private Long Id;
  private LocalDateTime createdDate;
  private Date updateDate;
  private String userName;
  private String email;
  private String telNo;
}
