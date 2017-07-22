package com.kwin.mybatis.mapper;

import java.util.List;

import com.kwin.mybatis.entity.Role;
import com.kwin.mybatis.entity.User;

public interface UserMapper {
	/**
	 * 通过id查询用户
	 * @param id
	 * @return
	 */
	User selectById(Long id);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> selectAll();
	
	/**
	 * 根据 userId 获取用户角色列表
	 * @param userId
	 * @return
	 */
	List<Role> selectRolesByUserId(Long userId);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	int insert(User user);
}
