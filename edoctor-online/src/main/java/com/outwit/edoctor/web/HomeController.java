package com.outwit.edoctor.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        if (1 == 1)
            throw new RuntimeException("fuck exception");
        return "Hello E doctor - OutwitIdea";
    }
}
