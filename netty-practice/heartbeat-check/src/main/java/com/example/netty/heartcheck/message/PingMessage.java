package com.example.netty.heartcheck.message;

import com.example.netty.heartcheck.constant.MessageType;

public class PingMessage extends AbsMessage {
    public PingMessage() {
        super();
        setMsgType(MessageType.PING);
    }
}
