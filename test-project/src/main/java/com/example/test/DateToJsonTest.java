package com.example.test;

import com.alibaba.fastjson.JSON;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试时间转换成字符串之后的格式
 */
public class DateToJsonTest {
	public static void main(String[] args) {
		// 经过测试 转换成字符之后是数字 1523696167976
		Date date = new Date();

		// 转换成字符之后是字符 "2018-04-14T16:56:08.037"
		LocalDateTime localDateTime = LocalDateTime.now();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		System.out.println(JSON.toJSONString(date));
		System.out.println(JSON.toJSONString(localDateTime));

		List<String> dd = new ArrayList<>();
		dd.add("rongrong");
		dd.add("lihao");
		String[] arrayStr = new String[dd.size()];
		dd.toArray(arrayStr);


	}
}
