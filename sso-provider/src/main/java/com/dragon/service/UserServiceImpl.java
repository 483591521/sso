package com.dragon.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dragon.common.SysResult;
import com.dragon.mapper.UserMapper;
import com.dragon.pojo.User;
import com.dragon.util.RedisUtils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisUtils redisUtils;
	
	// 根据type=1:username;2:phone;3:email
	private static final Map<Integer, String> PARAM_TYPE = new HashMap<>();
	// 初始化
	static {
		PARAM_TYPE.put(1, "username");
		PARAM_TYPE.put(2, "phone");
		PARAM_TYPE.put(3, "email");
	}
	public SysResult Check(String param, Integer type) {
		int i = userMapper.check(PARAM_TYPE.get(type), param);
		if (i == 0) {
			return SysResult.ok("false"); // 数据库没有，可用
		} else {
			return SysResult.build(201, "OK", "true"); // 数据库值已存在，不可用
		}
	}
	
	public Boolean register(User user) {
		// 判断，如果此用户已经存在，返回不能注册
		int i = userMapper.check(PARAM_TYPE.get(1), user.getUsername());
		if (i > 0) { // 用户已经存在
			return false;
		} else {			
			// 密码加密
			String newpassword = DigestUtils.md5Hex(user.getPassword());
			user.setPassword(newpassword);
			userMapper.save(user);
			return true;
		}	
	}
	
	public String login(String username, String password) {
		User user = userMapper.login(username, DigestUtils.md5Hex(password));
		if (user == null) {
			return null;
		} else {
			// 满足三个特性
			// 1.动态性
			// 2.唯一性
			// 3.混淆性
			String ticket = DigestUtils.md5Hex("TICKET_" + System.currentTimeMillis() + username);
			user.setPassword(null);
			// 登录成功，把user的json串写入到redis，key=ticket,value=user
			// 过期时间，电商中习惯用七天
			redisUtils.set(ticket, user, 60 * 60 * 24 * 7);
			return ticket;
		}
	}
	
	public String queryByTicket(String ticket) {
		// 根据ticket到redis中获取
		String userjson = (String) redisUtils.get(ticket);
		return userjson;
	}
	
}
