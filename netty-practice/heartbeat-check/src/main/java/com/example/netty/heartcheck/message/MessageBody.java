package com.example.netty.heartcheck.message;

import java.io.Serializable;

/**
 * 封装消息的实体
 */
public class MessageBody implements Serializable {

    private Object obj;

    public MessageBody() {
    }

    public MessageBody(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageBody{");
        sb.append("obj=").append(obj);
        sb.append('}');
        return sb.toString();
    }
}
