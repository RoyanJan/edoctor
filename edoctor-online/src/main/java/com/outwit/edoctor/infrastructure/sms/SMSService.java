package com.outwit.edoctor.infrastructure.sms;

public interface SMSService {

    public void sendMessage(String telephone,String msg);
}
