package com.jyt.dubboA.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jyt.dubboA.bean.User;
import com.jyt.dubboA.service.DataService;

@Service
public class DataServiceImpl implements DataService {
	
	private static Map<String,User> userMap = new HashMap<String,User>() ;

	@Override
	@Cacheable(value="userCache", key="#name")
	public User getUser(String name) {
		System.out.println("从MAP中获取了数据");
		return userMap.get(name);
	}

	@Override
	@CachePut(value="userCache", key="#user.name")
	public User saveUser(User user) {
		userMap.put(user.getName(), user) ;
		return user;
	}

}
