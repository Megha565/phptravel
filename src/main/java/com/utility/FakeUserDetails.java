package com.utility;

import java.util.HashMap;

import org.openqa.selenium.By;

import com.github.javafaker.Faker;

public class FakeUserDetails

{
	public static HashMap<String, String> getuserDetails() {
		HashMap<String, String> userdata = new HashMap<String, String>();
		Faker faker = new Faker();
		userdata.put("firstname", faker.name().firstName());
		userdata.put("lastname", faker.name().lastName());
		userdata.put("phone", String.valueOf(faker.phoneNumber().phoneNumber()));
		userdata.put("email", faker.internet().safeEmailAddress());
		userdata.put("password", "Test@123");
		userdata.put("confirmpassword",  "Test@123");
		return userdata;

	}
	
	
}
