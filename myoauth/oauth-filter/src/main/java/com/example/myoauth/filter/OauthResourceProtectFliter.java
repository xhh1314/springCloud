package com.example.myoauth.filter;

import com.example.base.rest.Rest;
import com.example.myoauth.authentication.Authentication;
import com.example.myoauth.constants.OauthHeaderConstants;
import com.example.myoauth.session.SessionProvider;
import com.example.myoauth.session.impl.LocalSessionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class OauthResourceProtectFliter implements Filter {

    private final SessionProvider sessionProvider = new LocalSessionProviderImpl();

    private String checkAccessTokenUrl = null;

    private HashSet<String> excludeUrL;


    RestTemplate restTemplate=new RestTemplate();

    public void setExcludeUrL(HashSet<String> excludeUrL) {
        this.excludeUrL = excludeUrL;
    }

    public void setCheckAccessTokenUrl(String checkAccessTokenUrl) {
        this.checkAccessTokenUrl = checkAccessTokenUrl;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        checkAccessTokenUrl = filterConfig.getInitParameter(OauthHeaderConstants.CHECK_ACESS_TOKEN_URL);
        if (checkAccessTokenUrl == null)
            throw new RuntimeException("checkAccessTokenUrl didn't initial");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().substring(request.getContextPath().length());
        if (excludeUrL != null && excludeUrL.contains(url)) {
            doFilter(servletRequest, servletResponse, chain);
        }
        String accessToken = request.getParameter(OauthHeaderConstants.ACCESS_TOKEN);
        String resourcesId = request.getParameter(OauthHeaderConstants.RESOURCES_ID);
        if (accessToken == null)
            return;
        //有可能没有resourceId,为了扩展用
        Authentication authentication = new Authentication(accessToken, resourcesId);
        if (sessionProvider.getAuthenticaiton(authentication) == null) {
            Map<String, String> parameter = new HashMap<>(4);
            parameter.put(OauthHeaderConstants.ACCESS_TOKEN, accessToken);
            if (resourcesId != null)
                parameter.put(OauthHeaderConstants.RESOURCES_ID, resourcesId);
            String checkUrl = checkAccessTokenUrl + "?" + OauthHeaderConstants.ACCESS_TOKEN + "={" + OauthHeaderConstants.ACCESS_TOKEN+"}";
            Rest rest = restTemplate.getForObject(checkUrl, Rest.class, parameter);
            if (rest.isSuccess() && (boolean) rest.getData()) {
                request.setAttribute(OauthHeaderConstants.AUTHENTICATION, authentication);
                doFilter(request, response, chain);
            } else {
                return;
            }
        } else {
            doFilter(request, response, chain);
        }


    }


    @Override
    public void destroy() {

    }
}
