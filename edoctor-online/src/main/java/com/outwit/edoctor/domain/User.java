package com.outwit.edoctor.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private String id;
    private String name;
    private String telephone;
    private UserType type;
    private String password;
    private String salt;
    private LocalDateTime createDate;
    private LocalDateTime lastAccess;

}
