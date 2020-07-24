package tdd.ytetdd;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
class YteTddApplicationTests {

	@Test
	void contextLoads() {
		Assert.assertEquals(5, 4 + 1);
	}

}
