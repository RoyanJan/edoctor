package com.outwit.edoctor.infrastructure.sms;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.outwit.edoctor.infrastructure.exception.ApplicationException;
import com.outwit.edoctor.infrastructure.exception.SystemCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Set;

@Slf4j
@Service("smsService")
public class SMSServiceImpl implements SMSService {

    @Autowired
    private SMSSettings smsSettings;

    private CCPRestSDK ccpRestSDK;

    @Override
    public void sendMessage(String telephone, String msg) {
        HashMap<String, Object> result = null;
        result = ccpRestSDK.sendTemplateSMS(telephone, smsSettings.getTemplateId(), new String[]{msg, "60秒"});
        log.info("SDKTestGetSubAccounts result=" + result);
        if ("000000".equals(result.get("statusCode"))) {
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                log.info(key + " = " + object);
            }
        } else {
            log.error("Telephone :" +telephone + " 错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
            throw new ApplicationException("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"), null, SystemCode.REMOTE_PROCESS_ERROR);
        }
    }

    @PostConstruct
    public void init() {
        ccpRestSDK = new CCPRestSDK();
        ccpRestSDK.init(smsSettings.getServerIp(), smsSettings.getServerPort());
        ccpRestSDK.setAccount(smsSettings.getAccountId(), smsSettings.getAuthToken());
        ccpRestSDK.setAppId(smsSettings.getTemplateId());
    }
}
