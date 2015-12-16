package com.brothers.sharegoods.function;

import com.brothers.sharegoods.model.UserBean;
import java.util.*;

public class TestClientStub {
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		UserBean t = ModelApplier.getUserById(1);
		System.out.println("Test for User Service : " + t.getUserId() + ", " + t.getUserName());
		
		UserBean ct = new UserBean();
		ct.setUserName("webservice");
		ct.setCity("ottawa");
		ct.setCountry("canada");
		ct.setEmail("webservice@gmail.com");
		ct.setPassword("webservice");
		ct.setSex("Male");
		String ctr = ModelApplier.createUser(ct);
		System.out.println("Test for Create User Service : " + ctr);

	}
}
