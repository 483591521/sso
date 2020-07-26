package com.dragon.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dragon.pojo.User;

// MybatisPlus实现单表CRUD操作，无需SQL语句
public interface UserMapper extends BaseMapper<User>{

	// 检查数据是否可用
	@Select("SELECT COUNT(*) FROM tb_user WHERE ${paramType}=#{param}")
	public Integer check(@Param("paramType") String paramType, @Param("param") String param);
	
	// 用户注册
	@Insert("INSERT INTO tb_user (username, password, phone, email, created, updated)VALUES(#{username}, #{password}, #{phone}, #{email}, now(), now())")
	public void save(User user);
	
	// 用户登录
	@Select("SELECT * FROM tb_user WHERE username=#{username} AND password=#{password}")
	public User login(@Param("username") String username, @Param("password") String password);
}
