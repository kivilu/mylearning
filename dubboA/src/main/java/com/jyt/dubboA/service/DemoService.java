/**
 * 
 */
package com.jyt.dubboA.service;

import com.jyt.dubboA.bean.User;

/**
 * 功能模块： 
 * Copyright: Copyright (c) 2016 
 * Create Date: 2016年2月23日
 * Company: www.jytpay.com 
 * @author  Eric
 * @version $Id: DemoService.java,v 1.0 Eric Exp $
 */
public interface DemoService {

	String testService( String name );
	
	User getUser( String name);
}
