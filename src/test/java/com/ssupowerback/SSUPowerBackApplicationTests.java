package com.ssupowerback;

import com.ssupowerback.crawling.Crawling;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SSUPowerBackApplicationTests {

	@Test
	void contextLoads() {
		Crawling c = new Crawling();
		c.process();

	}

}
