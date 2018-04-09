package com.example.myoauth.authentication;

/**
 * 权限的抽象
 *
 * @author lh
 * @date 2018/4/9
 * @since
 */
public class Authentication {

    private String accessToken;
    private String resourcesId;
    private long createTime;
    private boolean expired = false;

    public Authentication(String accessToken) {
        this.accessToken = accessToken;
        this.createTime = System.currentTimeMillis() / 1000;
    }

    public Authentication(String accessToken, String resourcesId) {
        this.accessToken = accessToken;
        this.resourcesId = resourcesId;
        this.createTime = System.currentTimeMillis() / 1000;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isExpired() {
        return expired;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Authentication{");
        sb.append("accessToken='").append(accessToken).append('\'');
        sb.append(", resourcesId='").append(resourcesId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authentication that = (Authentication) o;

        if (!accessToken.equals(that.accessToken)) return false;
        return resourcesId != null ? resourcesId.equals(that.resourcesId) : that.resourcesId == null;
    }

    @Override
    public int hashCode() {
        int result = accessToken.hashCode();
        result = 31 * result + (resourcesId != null ? resourcesId.hashCode() : 0);
        return result;
    }
}
