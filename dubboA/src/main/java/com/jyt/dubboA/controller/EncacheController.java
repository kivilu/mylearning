package com.jyt.dubboA.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jyt.dubboA.bean.User;
import com.jyt.dubboA.service.DataService;

@Controller
public class EncacheController {
	
	@Resource
	private DataService dataService;
	
	@RequestMapping("/getUser")
	public void getUser( @RequestParam(value="name",required=false)String name,
			HttpServletRequest request,
			HttpServletResponse response){
		if(StringUtils.isEmpty(name))
			name = "defaultName";
		
		User user = dataService.getUser(name);
		
		try {
			String text = "No user:"+name;
			if(user != null)
				text = "userName:"+user.getName()+"\r\nage:"+user.getAge();
			PrintWriter pw = response.getWriter();
			pw.println(text);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/addUserAge")
	public void addUserAge( @RequestParam(value="name",required=false)String name,
				HttpServletRequest request,
				HttpServletResponse response){
		if(StringUtils.isEmpty(name))
			name = "defaultName";
		
		User user = dataService.getUser(name);
		if(user == null){
			user = new User();
			user.setName(name);
			user.setAge( 0 );
		}
		
		user.setAge( user.getAge() + 1 );
		
		dataService.saveUser(user);
	}
}
