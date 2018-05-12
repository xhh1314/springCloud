package com.example.netty.heartcheck.message;


import com.example.netty.heartcheck.constant.ClientIdentity;
import com.example.netty.heartcheck.constant.ClientInfo;
import com.example.netty.heartcheck.constant.MessageType;

import java.io.Serializable;

public class AbsMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private ClientInfo client;

    private MessageType msgType;


    public AbsMessage() {
        client = new ClientInfo();
        client.setClientId(ClientIdentity.clientId);
        client.setAddress(ClientIdentity.address);
    }


    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public ClientInfo getClient() {
        return client;
    }
}
