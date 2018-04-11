package com.example.myoauth.filter;

import com.alibaba.fastjson.JSON;
import com.example.base.rest.Rest;
import com.example.myoauth.authentication.Authentication;
import com.example.myoauth.constants.OauthHeaderConstants;
import com.example.myoauth.session.SessionProvider;
import com.example.myoauth.session.impl.LocalSessionProviderImpl;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;

public class OauthResourceProtectFliter implements Filter {

    private final SessionProvider sessionProvider = new LocalSessionProviderImpl();

    private String checkAccessTokenUrl = null;

    private HashSet<String> excludeUrL;


    private RestTemplate restTemplate;

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

        //配置RestTemplate特性
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(2000);
        requestFactory.setConnectTimeout(2000);
        restTemplate = new RestTemplate(requestFactory);
        // 使用 utf-8 编码集的 conver 替换默认的 conver（默认的 string conver 的编码集为 "ISO-8859-1"）
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> val = iterator.next();
            if (val instanceof StringHttpMessageConverter) {
                iterator.remove();
                break;
            }
        }
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("utf-8"));
        messageConverters.add(stringHttpMessageConverter);
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

        if (accessToken == null) {
            Rest rest = Rest.failure(OauthHeaderConstants.ACCESS_DENIED, "no permission");
            writeResult(response, rest);
            return;
        }
        //有可能没有resourceId,为了扩展用
        Authentication authentication = new Authentication(accessToken, resourcesId);
        if (sessionProvider.getAuthenticaiton(authentication) == null) {
            Map<String, String> parameter = new HashMap<>(4);
            parameter.put(OauthHeaderConstants.ACCESS_TOKEN, accessToken);
            if (resourcesId != null)
                parameter.put(OauthHeaderConstants.RESOURCES_ID, resourcesId);
            String checkUrl = checkAccessTokenUrl + "?" + OauthHeaderConstants.ACCESS_TOKEN + "={" + OauthHeaderConstants.ACCESS_TOKEN + "}";
            Rest rest = restTemplate.getForObject(checkUrl, Rest.class, parameter);
            if (rest.isSuccess() && (boolean) rest.getData()) {
                request.setAttribute(OauthHeaderConstants.AUTHENTICATION, authentication);
                sessionProvider.createAuthentication(authentication);
                chain.doFilter(request,response);
            } else {
                Rest result = Rest.failure(OauthHeaderConstants.ACCESS_TOKEN_EXPIREED, "access_token is invalid!");
                writeResult(response, result);
            }
        } else {
            chain.doFilter(request,response);
        }


    }

    private void writeResult(HttpServletResponse response, Rest rest) throws IOException {
        PrintWriter out = null;
        response.addHeader("Content-Type","application/json");
        response.addHeader("charset","utf-8");
        out = response.getWriter();
        String resultStr = JSON.toJSONString(rest);
        out.write(resultStr);
    }


    @Override
    public void destroy() {

    }
}
