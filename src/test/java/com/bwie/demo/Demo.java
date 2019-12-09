package com.bwie.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bwie.utils.UserUtils;
import com.sunzelong.bean.User;

/**
 * @author 孙泽龙
 *
 * 2019年12月9日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:redis.xml")
public class Demo {
	@Autowired
	private RedisTemplate redisTemplate;
	//jdk序列化方式
	@Test
	public void testJDK(){
		//记录开始时间
		long start = System.currentTimeMillis();
		//生成5万条数据
		for (int i = 0; i < 50000; i++) {
			//创建对象
			User u = new User();
			//id
			u.setId(i);
			//调用工具类 随即生成姓名
			u.setName(UserUtils.getName());
			//随即生成性别
			u.setGender(UserUtils.getSex());
			//随即生成邮箱
			u.setEmail(UserUtils.getMail());
			//随即生成电话
			u.setPhoneNumber(UserUtils.getPhone());
			//随即生成生日
			u.setBirthday(UserUtils.getBirthday());
			//上传到redis数据库
			redisTemplate.opsForValue().set("user"+i, u);
		}
		//记录结束时间
		long end = System.currentTimeMillis();
		System.out.println("系列化方式为jdk");
		System.out.println("保存了5万条");
		//公用多少十年
		System.out.println("用时"+(end-start)+"毫秒");
		
		
	}
	//hash
	@Test
	public void testHASH(){
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			//创建对象
			User u = new User();
			//id
			u.setId(i);
			//调用工具类 随即生成姓名
			u.setName(UserUtils.getName());
			//随即生成性别
			u.setGender(UserUtils.getSex());
			//随即生成邮箱
			u.setEmail(UserUtils.getMail());
			//随即生成电话
			u.setPhoneNumber(UserUtils.getPhone());
			//随即生成生日
			u.setBirthday(UserUtils.getBirthday());
			//上传到redis数据库
			redisTemplate.opsForHash().put("user"+i, "user1"+i, u.toString());
			
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式为hash");
		System.out.println("保存了5万条");
		System.out.println("使用"+(end-start)+"毫秒");
		
		
	}
	//json
	@Test
	public void testJSON(){
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			//创建对象
			User u = new User();
			//id
			u.setId(i);
			//调用工具类 随即生成姓名
			u.setName(UserUtils.getName());
			//随即生成性别
			u.setGender(UserUtils.getSex());
			//随即生成邮箱
			u.setEmail(UserUtils.getMail());
			//随即生成电话
			u.setPhoneNumber(UserUtils.getPhone());
			//随即生成生日
			u.setBirthday(UserUtils.getBirthday());
			//上传到redis数据库
			redisTemplate.opsForValue().set("user"+i, u);
			
		}
		long end = System.currentTimeMillis();
		System.out.println("序列化方式为json");
		System.out.println("保存了5万条");
		System.out.println("使用"+(end-start)+"毫秒");
		
		
	}
}
