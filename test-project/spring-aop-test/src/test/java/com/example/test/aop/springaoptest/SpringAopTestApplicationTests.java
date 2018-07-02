package com.example.test.aop.springaoptest;

import com.example.test.aop.springaoptest.cglib.HelloMethodInterceptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAopTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SpringAopTestApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
	}

	@Test
	public void jdkDynamicAopProxy() {
		Person person = applicationContext.getBean(Person.class);
		person.say();
		person.say();
	}

	@Test
	public void cglibAopTest() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PersonImpl.class);
		enhancer.setCallback(new HelloMethodInterceptor());
		PersonImpl person = (PersonImpl) enhancer.create();
		person.say();

	}

}
