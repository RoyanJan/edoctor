package com.outwit.edoctor.infrastructure.sms;

import org.springframework.stereotype.Service;

@Service("smsService")
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendMessage(String msg) {
        // TODO 调用短信平台
    }
}
