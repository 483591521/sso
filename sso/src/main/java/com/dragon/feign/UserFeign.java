package com.dragon.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dragon.common.SysResult;
import com.dragon.pojo.User;

@FeignClient("sso-provider")
public interface UserFeign {

	@RequestMapping("/user/check/{param}/{type}")
	public SysResult check(@PathVariable("param") String param, @PathVariable("type") Integer type);
	
	@RequestMapping("/user/register")
	public SysResult register(User user); // 改造服务提供者，@RequestBody,使用JSON方式传参
	// Feign的底层选择REST+json的方式。
	
	@RequestMapping("/user/login")
	public SysResult login(@RequestParam("u") String username, @RequestParam("p") String password);
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult queryByTicket(@PathVariable("ticket") String ticket);
}
