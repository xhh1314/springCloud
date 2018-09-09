package com.example.test.applicationjsontest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ApplicationJsonTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationJsonTestApplication.class, args);
	}


	@RequestMapping(value = "/test1")
	@CrossOrigin(origins = {"*"})
	public Person test1(@RequestBody String s){
		System.out.println("收到对象:"+s);
		JSONArray a = JSONArray.parseArray(s);
		System.out.println(a.size());
		JSONObject ret = new JSONObject();
		for (Object o : a.toArray()) {
			JSONObject j = JSON.parseObject((String) o);
			ret.put(j.getString("name"), j.get("value"));
		}
		Person person=new Person();
		person.setName("成功");
		person.setAge(22);
		return person;
	}
}
