package com.outwit.edoctor.service;

import com.outwit.edoctor.Application;
import com.outwit.edoctor.config.WebContextConfig;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({WebContextConfig.class, Application.class})
@WebIntegrationTest(randomPort = true)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void test() {
//        userService.sendVerifyCode("sessionid0001");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("18510247299","jcy");
        subject.login(token);
        assertTrue(subject.isAuthenticated());
    }
}
