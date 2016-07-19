package com.outwit.edoctor.service;

import com.outwit.edoctor.Application;
import com.outwit.edoctor.config.WebConfig;
import com.outwit.edoctor.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({WebConfig.class, Application.class})
@WebIntegrationTest(randomPort = true)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = userService.findUserByTelephone("18510247299");
        System.out.println(user.toString());
    }
}
