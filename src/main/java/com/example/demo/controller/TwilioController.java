package com.example.demo.controller;

import com.example.demo.model.MessageModel;
import com.example.demo.service.TwilioMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TwilioController {

    @Autowired
    private TwilioMessageSenderService twilioService;

    public TwilioController(TwilioMessageSenderService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("/twilio/sms")
    public String sendSmsByTwilio(@RequestBody MessageModel messageRequest) {
        String sendMessageResponse = twilioService.sendSmsByTwilio(messageRequest);
        return sendMessageResponse;
    }

    @PostMapping("/twilio/call")
    public String initiateCallByTwilio(@RequestBody MessageModel messageRequest) {
        String initiateCallResponse = twilioService.initiateCallByTwilio(messageRequest);
        return initiateCallResponse;
    }

    @PostMapping("nexmo/sms")
    public String sendSmsByNexmo(@RequestBody MessageModel messageModel) {
        String sendMessage = twilioService.sendSmsByNexmo(messageModel);
        return sendMessage;
    }

}
