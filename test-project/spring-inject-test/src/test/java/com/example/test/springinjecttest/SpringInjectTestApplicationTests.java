package com.example.test.springinjecttest;

import com.example.test.springinjecttest.api.PersonApi;
import com.example.test.springinjecttest.config.RootClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringInjectTestApplicationTests {

	@Autowired
	private PersonApi personApi;

	@Autowired
	private RootClass rootClass;

	@Test
	public void contextLoads() {
		System.out.println(personApi.personDetail("lihao"));

	}

}
