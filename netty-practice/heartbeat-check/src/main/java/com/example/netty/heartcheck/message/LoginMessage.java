package com.example.netty.heartcheck.message;

import com.example.netty.heartcheck.constant.MessageType;

public class LoginMessage extends AbsMessage {

    public LoginMessage() {
        super();
        setMsgType(MessageType.LOGIN);
    }
}
