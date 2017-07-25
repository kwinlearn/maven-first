package com.kwin.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 新增用户 - 使用 useGeneratedKeys 方式
	 * @param user
	 * @return
	 */
	int insert2(User user);
	
	/**
	 * 新增用户 selectKey
	 * @param user
	 * @return
	 */
	int insert3(User user);
	
	/**
	 * 根据主键更新
	 * @param user
	 * @return
	 */
	int updateById(User user);
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	int deleteById(Long id);
	
	/**
	 * 根据用户 id 和角色的 enabled 状态获取用户的角色
	 * @param userId
	 * @param enabled
	 * @return
	 */
	List<Role> selectRolesByUserIdAndRoleEnabled(
			@Param("userId")Long userId, 
			@Param("enabled")Integer enabled);
	
	/**
	 * 根据动态条件查询用户信息
	 * @return
	 */
	List<User> selectByUser(User user);
	
	/**
	 * 根据主键更新
	 * @param user
	 * @return
	 */
	int updateByIdSelective(User user);
	
	/**
	 * 根据永辉 id 或用户名查询
	 * @param user
	 * @return
	 */
	User selectByIdOrUserName(User user);
}
