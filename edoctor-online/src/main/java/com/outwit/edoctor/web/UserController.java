package com.outwit.edoctor.web;

import com.outwit.edoctor.infrastructure.Term.Interaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {


    @RequestMapping(path = "/verifyCode",method = RequestMethod.GET)
    public Interaction fetchVerifyCode(HttpSession session){

        return new Interaction(null);
    }

}
