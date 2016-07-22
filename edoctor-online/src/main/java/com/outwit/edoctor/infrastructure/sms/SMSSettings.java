package com.outwit.edoctor.infrastructure.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "sms")
@Component("smsSettings")
public class SMSSettings {

    private String serverIp;
    private String serverPort;
    private String accountId;
    private String authToken;
    private String templateId;
}

