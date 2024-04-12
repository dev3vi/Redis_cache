package com.example.redis_cache;

import com.example.redis_cache.Constant.Constants;
import com.example.redis_cache.model.Person;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RedisCacheApplication implements CommandLineRunner {

    @Autowired
    RedisTemplate redisTemplate;
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        addWithList();
    }

    private void addWithHash() {
        Person person = new Person(1, "Nguyen van a2", 18);
        redisTemplate.opsForHash().put("key3" ,"thaolv", GSON.toJson(person));
        System.out.println(redisTemplate.opsForHash().get("key3", "thaolv"));

    }

    private void addWithValue() {
        Person person = new Person(1, "Nguyen van a222", 18);
        redisTemplate.opsForValue().set(Constants.KEY , GSON.toJson(person));
        System.out.println(redisTemplate.opsForValue().get(Constants.KEY));
    }

    private void addWithList() {
        Person person = new Person(1, "Nguyen van a", 18);
        Person person2 = new Person(2, "Nguyen van a2", 19);
        List<Person> persons = Arrays.asList(person, person2);
        redisTemplate.opsForList().rightPushAll("key2", persons);
        //remove and return the last element(on the right) of list data
        System.out.println(redisTemplate.opsForList().rightPop("key2"));
    }
}
