package com.example.test.aop.springaoptest;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyFactoryBeanConfig {

	@Bean
    @Qualifier("person")
	public ProxyFactoryBean getProxyFactoryBean() throws ClassNotFoundException {
		ProxyFactoryBean proxy = new ProxyFactoryBean();
		//proxy.setProxyInterfaces(new Class[] { Person.class });
		proxy.setTarget(new PersonImpl());
		proxy.setInterceptorNames("myBeforeAdvice");
		return proxy;
	}

}
