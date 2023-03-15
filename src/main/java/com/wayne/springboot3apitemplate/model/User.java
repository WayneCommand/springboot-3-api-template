package com.wayne.springboot3apitemplate.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

  private Long userId;
  private String userName;
  private String userEmail;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime modifyAt;


}
