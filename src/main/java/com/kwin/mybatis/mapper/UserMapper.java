package com.kwin.mybatis.mapper;

import com.kwin.mybatis.entity.User;

public interface UserMapper {
	/**
	 * 通过id查询用户
	 * @param id
	 * @return
	 */
	User selectById(Long id);
}
