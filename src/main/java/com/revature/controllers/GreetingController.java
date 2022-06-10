package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.exceptions.NotLoggedInException;
import com.revature.messagingstompwebsocket.Greeting;
import com.revature.messagingstompwebsocket.HelloMessage;
import com.revature.models.Messages;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.OnMessage;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
public class GreetingController {


    private final AuthService authService;
    private final MessageService messageService;

    @Autowired
    public GreetingController(AuthService authService, MessageService messageService) {
        this.authService = authService;
        this.messageService = messageService;
    }

    @Authorized
    @MessageMapping("/hello")
    @SendTo("/topics/greetings")
    @PostMapping("/hello")
    public ResponseEntity<Messages> messages(@RequestBody Messages message, HttpSession session) throws Exception {

        Thread.sleep(1000); // simulated delay

        System.out.println("coming form line 41 " + session);

        // If the user is not logged in
        if(session.getAttribute("user") == null) {
            return null;
        } else {
            User u = (User)session.getAttribute("user");
            message.setSender(u.getId());
            message.setReceiver(2);
            message.setMsgId(0);;

//            messageService.save(message);
            System.out.println("coming from line 29 " + message.getName() );
        }

        return ResponseEntity.ok(messageService.save(message));

    }


    @Authorized
    @MessageMapping("/topics/greetings")
    @SendTo("/hello")
    @GetMapping()
    public Greeting greeting2() throws Exception {
        Thread.sleep(1000); // simulated delay



        return null;
//        System.out.println("coming from line 29 " +  );
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}