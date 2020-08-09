package com.websocketdemo.model;

//import lombok.AllArgsConstructor;

import lombok.Data;

@Data
//@AllArgsConstructor
public class ServerMessage {

    private String content;

    public ServerMessage() {
    }

    public ServerMessage(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

}
