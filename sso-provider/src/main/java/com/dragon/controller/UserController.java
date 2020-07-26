package com.dragon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dragon.common.SysResult;
import com.dragon.pojo.User;
import com.dragon.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/check/{param}/{type}")
	public SysResult check(@PathVariable String param, @PathVariable Integer type) {
		return userService.Check(param, type);
	}
	
	@RequestMapping("/register") // 以post提交其他参数
	public SysResult register(@RequestBody User user) {
		Boolean b = userService.register(user);
		if (b) {
			return SysResult.ok(user.getUsername());
		} else {
			return SysResult.build(201, user.getUsername() + "用户注册失败");
		}
	}
	
	// @RequestParam注解实现页面post请求的u参数映射到username
	@RequestMapping("/login")
	public SysResult login(@RequestParam("u") String username, @RequestParam("p") String password) {
		String ticket = userService.login(username, password);
		if (ticket == null) {
			return SysResult.build(201, "用户名或密码错误，请重新登录");
		} else {
			return SysResult.ok(ticket);
		}
	}
	
	@RequestMapping("/query/{ticket}")
	public SysResult queryByTicket(@PathVariable String ticket) {
		String userjson = userService.queryByTicket(ticket);
		if (userjson != null) {
			return SysResult.ok(userjson);
		} else {
			return SysResult.build(201, "没有查询到");
		}
	}
}
