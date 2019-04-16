package com.atguigu.gmall.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.atguigu.gmall.manager.mapper.BaseCatalog1Mapper;
import com.atguigu.gmall.manager.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManagerServiceApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    CatalogService catalogService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    JedisPool jedisPool;

    @Test
    public void TestJedis(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("myJedis","666中文");
    }

    /*
    * String hash list set zset
    * */
    @Test
    public void testRedis(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("hello","world",20, TimeUnit.SECONDS);
        System.out.println("设置完成！");
        String hello = stringStringValueOperations.get("hello");
        System.out.println("返回值：" + hello);
    }

    @Test
    public void testCataSer(){
        List<BaseCatalog1> allBaseCatalog1 = catalogService.getAllBaseCatalog1();
        System.out.println(allBaseCatalog1);
    }

    @Test
    public void testMapper(){
        BaseCatalog1 baseCatalog1 = new BaseCatalog1();
        baseCatalog1.setName("lxm");
        List<BaseCatalog1> baseCatalog1s = baseCatalog1Mapper.selectList(null);
        log.info(baseCatalog1s.toString());
    }

    @Test
    public void delUser(){
        userMapper.deleteById(1L);
        System.out.println("OKOKOKOKOKOK");
        for (User user : userMapper.selectList(null)
        ) {
            System.out.println(user);
        }
    }

    @Test
    public void contextLoads() {
        for (User user : userMapper.selectList(null)
             ) {
            System.out.println(user);
        }
        System.out.println("==================");
        User user = new User();
        user.setAge(20);
        user.setName("Jack");
        User userHaha = userMapper.getUserHaha(user);
        System.out.println(userHaha);
    }
}
