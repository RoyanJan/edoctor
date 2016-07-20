package com.outwit.edoctor.web;

import com.outwit.edoctor.infrastructure.validators.FixLength;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RegistDTO implements Serializable {

    @NotNull
    @FixLength(length = 11)
    private String telephone;
    @Length(min = 9)
    private String plainTextPassword;
    @NotNull
    @FixLength(length = 6)
    private String verifyCode;
    @NotNull
    private boolean isUser;

    public boolean getIsUser() {
        return isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }
}
