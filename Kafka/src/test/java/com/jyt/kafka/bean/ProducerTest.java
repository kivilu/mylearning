package com.jyt.kafka.bean;

import java.util.Random;

import kafka.serializer.StringEncoder;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import com.jyt.kafka.serviceImpl.Producer;

public class ProducerTest {
	
	private static final String CONFIG = "/spring-kafka-productor.xml";
	private static Random rand = new Random();

	/*public static void main(String[] args) {
		
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, ProducerTest.class);
		ctx.start();

		final MessageChannel channel = ctx.getBean("inputToKafka", MessageChannel.class);

		for (int i = 0; i < 100; i++) {
			channel.send(MessageBuilder.withPayload("Message-" + rand.nextInt()).setHeader("messageKey", String.valueOf(i)).setHeader("topic", "test").build());
		}

		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ctx.close();
	}*/

	public static void main(String[] args) {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring.xml", ProducerTest.class);
		ctx.start();
		final Producer producer = ctx.getBean("producer", Producer.class);
		producer.product();
		ctx.close();
	}

}
