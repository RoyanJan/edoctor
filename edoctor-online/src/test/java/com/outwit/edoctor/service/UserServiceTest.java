package com.outwit.edoctor.service;

import com.outwit.edoctor.Application;
import com.outwit.edoctor.config.WebContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({WebContextConfig.class, Application.class})
@WebIntegrationTest(randomPort = true)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.sendVerifyCode("sessionid0001");
    }
}
