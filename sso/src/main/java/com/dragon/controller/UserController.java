package com.dragon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.common.SysResult;
import com.dragon.feign.UserFeign;
import com.dragon.pojo.User;

@RestController
public class UserController {

	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("/user/check/{param}/{type}")
	public SysResult check(@PathVariable("param") String param, @PathVariable("type") Integer type) {
		return userFeign.check(param, type);
	}
	
	@RequestMapping("/user/register")
	public SysResult register(User user) {
		return userFeign.register(user);
	}
	
	@RequestMapping("/user/login")
	public SysResult login(@RequestParam("u") String username, @RequestParam("p") String password) {
		return userFeign.login(username, password);
	}
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult queryByTicket(@PathVariable("ticket") String ticket) {
		return userFeign.queryByTicket(ticket);
	}
}
