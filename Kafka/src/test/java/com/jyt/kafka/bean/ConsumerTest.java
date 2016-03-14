/*package com.jyt.kafka.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.jyt.kafka.serviceImpl.Consumer;

public class ConsumerTest {

	public static void main(String[] args) {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring.xml", ConsumerTest.class);
		ctx.start();
		final Consumer consumer = ctx.getBean("consumer", Consumer.class);
		consumer.comsume();
		ctx.close();
	}

}
*/