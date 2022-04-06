package com.example.demo.controller;

import com.example.demo.model.MessageModel;
import com.example.demo.service.TwilioMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/twilio")
public class TwilioController {

    @Autowired
    private TwilioMessageSenderService twilioService;

    public TwilioController(TwilioMessageSenderService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("makeCall")
    public String sendSMSByTwilio(@RequestBody MessageModel messageRequest) {
        String sendMessageResponse = twilioService.sendMessage(messageRequest);
        return sendMessageResponse;
    }

    @PostMapping("sms")
    public String sendSms(@RequestBody MessageModel messageModel) {
        String sendMessage = twilioService.sendSms(messageModel);
        return sendMessage;
    }

}
