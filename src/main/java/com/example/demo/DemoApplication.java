package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

//Twilio call
//    public static final String ACCOUNT_SID = "ACe119deccdffd2869f5049f8764fda941";
//    public static final String AUTH_TOKEN = "00e9655b3fb145c00c3d7892e10428e8";
//
//    public static void main(String[] args) throws URISyntaxException {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Call call = Call.creator(
//                        new com.twilio.type.PhoneNumber("+94771746609"),
//                        new com.twilio.type.PhoneNumber("+15168530156"),
//                        new URI("http://79d8-2402-4000-2382-48f3-49b1-7ecd-af4f-f643.ngrok.io/doNewPost"))
//                .create();
//
//        System.out.println(call.getSid());
//        System.out.println(call.getAccountSid());
//        System.out.println(call.getParentCallSid());
//        System.out.println(call.getTo());
//
//    }
//}


//    public static final String ACCOUNT_SID = "ACe119deccdffd2869f5049f8764fda941";
//    public static final String AUTH_TOKEN = "00e9655b3fb145c00c3d7892e10428e8";
//
//    public static void main(String[] args) {
//
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                        new com.twilio.type.PhoneNumber("+94771746609"),
//                        new com.twilio.type.PhoneNumber("+15168530156"),
//                        "Where's Wallace?")
//                .create();
//
//        System.out.println(message.getSid());
//
//    }
