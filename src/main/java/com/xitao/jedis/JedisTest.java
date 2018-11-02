package com.xitao.jedis;

import com.xitao.sort.TestUtils;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RHyperLogLog;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JedisTest {

    public static RedissonClient createClient() {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    @Test
    public void hllTest() {
        List<Integer> ls = new ArrayList<>();
        RedissonClient redissonClient = createClient();
        RHyperLogLog rHyperLogLog = redissonClient.getHyperLogLog("dddd");
        for(int i=0;i<10;i++) {
            ls = Arrays.asList(TestUtils.createRandomArrayInteger(100000, 10000));
            rHyperLogLog.addAll(ls);

        }
        System.out.println(rHyperLogLog.count());

    }
}
