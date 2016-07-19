package com.outwit.edoctor.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationDTO implements Serializable {

    private String telephone;
    private String plainTextPassword;
    private String verifyCode;
    private boolean isUser;

    public boolean getIsUser(){
        return isUser;
    }
    public void setIsUser(boolean isUser){
        this.isUser = isUser;
    }
}
