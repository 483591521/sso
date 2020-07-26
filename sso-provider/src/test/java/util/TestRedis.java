package util;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedis {

	@Test
	public void jedis() {
		Jedis jedis = new Jedis("192.168.137.134", 6379);
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
	}
}
