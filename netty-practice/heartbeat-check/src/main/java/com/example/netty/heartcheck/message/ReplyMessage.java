package com.example.netty.heartcheck.message;

import com.example.netty.heartcheck.constant.MessageType;

public class ReplyMessage extends AbsMessage {

    private MessageBody body;

    public ReplyMessage() {
        super();
        setMsgType(MessageType.REPLY);
    }

    public MessageBody getBody() {
        return body;
    }

    public void setBody(MessageBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReplyMessage{");
        sb.append("body=").append(body);
        sb.append('}');
        return sb.toString();
    }
}
