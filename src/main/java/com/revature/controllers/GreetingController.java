package com.revature.controllers;

import com.revature.messagingstompwebsocket.Greeting;
import com.revature.messagingstompwebsocket.HelloMessage;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;



import javax.websocket.OnMessage;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topics/greetings")
    @PostMapping("/hello")
    public Greeting greeting(@RequestBody HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay

        System.out.println("coming from line 29 " + message.getName() );
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


    @GetMapping()
    public Greeting greeting2(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay

        System.out.println("coming from line 29 " + message.getName() );
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}