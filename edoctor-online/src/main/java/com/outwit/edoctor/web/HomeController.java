package com.outwit.edoctor.web;

import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeTest() {
        return "fuck";
    }
}
