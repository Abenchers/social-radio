package com.abenchers.socialradio.contentinformation.context;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abenchers.socialradio.streaming.vlcimpl.ServerVLC;

public class ContextTest {

	@Autowired
	private ServerVLC serverVLC;
	
	private ApplicationContext applicationContext;
	
	@Before
	public void setUp(){
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@Test
	public void shouldHaveNotContextErrors() throws InterruptedException {

		serverVLC = (ServerVLC) applicationContext.getBean("serverVLC");
		serverVLC.stop();
		((ClassPathXmlApplicationContext) applicationContext).close(); 
	}

}
