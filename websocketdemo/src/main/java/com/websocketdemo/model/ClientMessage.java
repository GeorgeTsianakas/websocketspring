package com.websocketdemo.model;

//import lombok.AllArgsConstructor;

import lombok.Data;

@Data
//@AllArgsConstructor
public class ClientMessage {

    private String name;

    public ClientMessage() {
    }

    public ClientMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
