package org.example.demo;

import org.example.demo.json.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private ApplicationContext appCtx;

	@Test
	void contextLoads() {
		assertNotNull(this.appCtx);
		System.out.println("Beans # " + this.appCtx.getBeanDefinitionCount());
		Arrays.stream(this.appCtx.getBeanDefinitionNames()).forEach(System.out::println);
	}

	@Test
	void testGreetingBean() {
		Greeting greeting1 = this.appCtx.getBean("defaultGreeting", Greeting.class),
				 greeting2 = this.appCtx.getBean("myGreeting", Greeting.class);
		System.out.println(greeting1);
		System.out.println(greeting2);
		Arrays.stream(this.appCtx.getBeanNamesForType(Greeting.class)).forEach(System.out::println);
	}
}
