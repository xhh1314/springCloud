package com.example.netty.heartcheck.constant;

import java.io.Serializable;

public class ClientInfo implements Serializable {

    public String clientId;

    public String address;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("clientId='").append(clientId).append('\'');
        sb.append(", address='").append(address).append('\'');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientInfo that = (ClientInfo) o;

        if (!clientId.equals(that.clientId)) return false;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
