package com.jyt.kafka.serviceImpl;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
		@Resource
		private MessageChannel inputToKafka ;

		//private static final String CONFIG = "/context.xml";
		private static Random rand = new Random();
		
		
		public void product(){
			//final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, Producer.class);
			//ctx.start();
			//final MessageChannel channel = ctx.getBean("inputToKafka", MessageChannel.class);
			for (int i = 0; i < 100; i++) {
				String msg = "Message-" + i ;//rand.nextInt() ;
				MessageBuilder<String> msgBld = MessageBuilder.withPayload(msg) ;
				MessageBuilder<String> bld = msgBld.setHeader("messageKey", String.valueOf(i)).setHeader("topic", "test") ;
				Message<String> bean = bld.build() ;
				inputToKafka.send(bean );
				System.out.println( msg );
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//ctx.close();
		}


}
