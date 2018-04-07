package com.example.myoauth;

import java.util.UUID;

public class OtherTest {

	public static void main(String[] args) {
		String str = Long.toString((long) (Math.random() * Long.MAX_VALUE), 72);
		System.out.println(str + "length:" + str.length());
		String str2 = UUID.randomUUID().toString();
		System.out.println(str2 + "length:" + str2.length());
		String str3 = str2.replace("-", "").substring(0, 24);
        System.out.println(str3 + "length:" + str3.length());
	}
}
