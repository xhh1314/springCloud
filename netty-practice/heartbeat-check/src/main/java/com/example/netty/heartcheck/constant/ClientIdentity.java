package com.example.netty.heartcheck.constant;

public class ClientIdentity {

    public static String clientId;

    public static String address;

    public ClientIdentity(String clientId, String address) {
        ClientIdentity.clientId = clientId;
        ClientIdentity.address = address;
    }
}
