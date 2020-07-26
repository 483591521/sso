package com.dragon.service;

import org.apache.ibatis.annotations.Param;

import com.dragon.common.SysResult;
import com.dragon.pojo.User;

public interface UserService {

	public SysResult Check(String param, Integer type);
	
	public Boolean register(User user);
	
	public String login(String username, String password);
	
	public String queryByTicket(String ticket);
}
