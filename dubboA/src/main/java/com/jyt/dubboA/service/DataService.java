package com.jyt.dubboA.service;

import com.jyt.dubboA.bean.User;

public interface DataService {
	public User getUser( String name );
	
	public User saveUser( User user );
}
