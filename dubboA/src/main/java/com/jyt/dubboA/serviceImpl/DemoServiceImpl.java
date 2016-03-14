package com.jyt.dubboA.serviceImpl;

import org.springframework.stereotype.Service;

import com.jyt.dubboA.bean.User;
import com.jyt.dubboA.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Override
	public String testService(String name) {
		// TODO Auto-generated method stub
		return "New-"+name;
	}

	@Override
	public User getUser(String name) {

		User user = new User();
		user.setAge(30);
		user.setName(name);
		return user;
	}

}
