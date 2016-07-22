package com.outwit.edoctor.service;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.outwit.edoctor.Application;
import com.outwit.edoctor.config.WebConfig;
import com.outwit.edoctor.domain.User;
import com.outwit.edoctor.infrastructure.exception.SystemCode;
import com.outwit.edoctor.infrastructure.sms.SMSService;
import com.outwit.edoctor.infrastructure.sms.SMSServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({WebConfig.class, Application.class})
@WebIntegrationTest(randomPort = true)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Autowired
    private SMSService smsService;

    @Test
    public void test() {
//        HashMap<String, Object> result = null;
//        CCPRestSDK restAPI = new CCPRestSDK();
//        restAPI.init("app.cloopen.com", "8883");
//        // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
//        restAPI.setAccount("8a216da85607361a01560cf442970635", "57ef727e646c4699bd4cfb91b9930c70");
//        // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在"控制台-应用"中看到开发者主账号ACCOUNT SID和
//        //主账号令牌AUTH TOKEN。
//        restAPI.setAppId("8a216da85607361a01560cf442fc063b");
//        // 初始化应用ID，如果是在沙盒环境开发，请配置"控制台-应用-测试DEMO"中的APPID。
//        //如切换到生产环境，请使用自己创建应用的APPID
//        result = restAPI.sendTemplateSMS("18510247299","登录验证模版" ,new String[]{"888888","60s"});
//        System.out.println("SDKTestGetSubAccounts result=" + result);
//        if("000000".equals(result.get("statusCode"))){
//            //正常返回输出data包体信息（map）
//            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//            Set<String> keySet = data.keySet();
//            for(String key:keySet){
//                Object object = data.get(key);
//                System.out.println(key +" = "+object);
//            }
//        }else{
//            //异常返回输出错误码和错误信息
//            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//        }
        smsService.sendMessage("18510247299","555555");
    }
}
