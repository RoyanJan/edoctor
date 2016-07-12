package com.outwit.edoctor.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;
    private String name;
    private String telephone;
    private UserType type;
    private String password;
    private String salt;
    private Date createDate;
    private Date lastAccess;

}
