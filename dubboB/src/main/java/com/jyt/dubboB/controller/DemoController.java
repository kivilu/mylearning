/**
 * 
 */
package com.jyt.dubboB.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyt.dubboA.bean.User;
import com.jyt.dubboA.service.DemoService;



/**
 * 功能模块：代收付应用层
 * Company:  
 * @author 
 * @version $Id: DemoController.java,v 1.0 Eric.Lu Exp $
 */

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	private static final Log log = LogFactory.getLog(DemoController.class);
	@Resource
	private DemoService demoService;
	
	
	@RequestMapping("/test")
	public String httpAllow(HttpServletRequest request,
			  HttpServletResponse response)throws Exception{
		
		response.getWriter().println(demoService.testService("test Dubbo!"));
		return null; 
	}
	
	
	@RequestMapping("/getUser")
	public String getUser(HttpServletRequest request,
			  HttpServletResponse response)throws Exception{
		
		User user = demoService.getUser("Tom");
		
		response.getWriter().println( user.getName() +", age="+user.getAge());
		return null; 
	}
	
}
