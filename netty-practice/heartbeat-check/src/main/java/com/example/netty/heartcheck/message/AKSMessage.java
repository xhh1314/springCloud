package com.example.netty.heartcheck.message;

import com.example.netty.heartcheck.constant.MessageType;

public class AKSMessage extends AbsMessage {
    public AKSMessage() {
        super();
        setMsgType(MessageType.AKS);
    }
}
