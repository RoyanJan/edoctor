package com.outwit.edoctor.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hello E doctor - OutwitIdea com.";
    }

    @RequiresRoles("NORMAL")
    @RequestMapping(value = "/userhome", method = RequestMethod.GET)
    public String authorizedUserTest() {
        return "user home";
    }

    @RequiresRoles("DOCTOR")
    @RequestMapping(value = "/doctorhome", method = RequestMethod.GET)
    public String authorizedDoctorTest() {
        return "doctor home";
    }
}
