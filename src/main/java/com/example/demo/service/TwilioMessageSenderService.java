package com.example.demo.service;

import com.example.demo.model.MessageModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Call;

import java.net.URI;

@Service
public class TwilioMessageSenderService {

    private static final Logger logger = LoggerFactory.getLogger(TwilioMessageSenderService.class);

    @Value("${accountSID}")
    private String accountSID;

    @Value("${accountAuthToken}")
    private String accountAuthToken;

    @Value("${twilioSenderNumber}")
    private String twilioSenderNumber;

    @Value("${nexmoAPIKey}")
    private String nexmoAPIKey;

    @Value("${nexmoAPISecret}")
    private String nexmoAPISecret;

    public String sendSmsByNexmo(MessageModel messageModel) {
        try {
            String toNumber = messageModel.getMobileNumber();
            String messageBody = messageModel.getSmsText();

            VonageClient client = VonageClient.builder().apiKey(nexmoAPIKey).apiSecret(nexmoAPISecret).build();
            TextMessage message = new TextMessage("Vonage APIs", toNumber, messageBody);
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

            if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                return "Message sent successfully.";
            } else {
                return "Message failed with error: " + response.getMessages().get(0).getErrorText();
            }
        } catch (Exception e) {
            return "Failed to send message via nexmo.";
        }

    }

    public String initiateCallByTwilio(MessageModel messageRequest) {
        try {
            Twilio.init(accountSID, accountAuthToken);
            String mobileNumber = messageRequest.getMobileNumber();

            Call call = Call.creator(
                            new com.twilio.type.PhoneNumber(mobileNumber),
                            new com.twilio.type.PhoneNumber(twilioSenderNumber),
                            new URI("http://3cb1-2402-4000-2281-e6cf-dcaf-9390-5a8c-b9cc.ngrok.io/doNewPost"))
                    .create();

            logger.info("Call initiated Successfully to the number " + mobileNumber);
            return "Call initiated Successfully with "
                    + " sid : " + call.getSid()
                    + "\nAccount sid " + call.getAccountSid()
                    + "\nParent sid " + call.getParentCallSid()
                    + "\ngetTo " + call.getTo();

        } catch (Exception e) {
            logger.error("Exception in call" + e);
            return "Failed to initiate call.";
        }
    }

    public String sendSmsByTwilio(MessageModel messageRequest) {

        try {
            Twilio.init(accountSID, accountAuthToken);

            String mobileNumber = messageRequest.getMobileNumber();
            String smsText = messageRequest.getSmsText();

            MessageCreator creator = com.twilio.rest.api.v2010.account.Message.creator(
                    new com.twilio.type.PhoneNumber(mobileNumber),
                    new com.twilio.type.PhoneNumber(twilioSenderNumber), smsText);
            Message create = creator.create();

            return "Message sent successfully via Twilio.";

        } catch (Exception e) {
            return "Message failed to send.";
        }
    }
}


// String smsText = messageRequest.getSmsText();
//            PhoneNumber receiverPhoneNumber = new PhoneNumber(mobileNumber);
//            PhoneNumber senderTwilioPhoneNumber = new PhoneNumber(twilioSenderNumber);
//            MessageCreator creator = com.twilio.rest.api.v2010.account.Message
//                    .creator(receiverPhoneNumber, senderTwilioPhoneNumber, smsText);
//            Message create = creator.create();