package com.websocketdemo.controller;

import com.websocketdemo.config.GlobalConstants;
import com.websocketdemo.model.ClientMessage;
import com.websocketdemo.model.ServerMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping(GlobalConstants.HELLO_MAPPING)
    @SendTo(GlobalConstants.TOPIC)
    public ServerMessage greeting(ClientMessage message) throws Exception {
        Thread.sleep(1000);
        return new ServerMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
