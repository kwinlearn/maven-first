package com.kwin.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.type.JdbcType;

import com.kwin.mybatis.entity.Role;

public interface RoleMapper {
	
	/**
	 * resultMap
	 */
	@Results(id = "BaseResultMap", value = {							// MyBatis 3.3.1 版本及以上才可以使用 id
		@Result(property = "id", column = "id", id = true),				// id = true   设置主键
		@Result(property = "roleName", column = "role_name"),
		@Result(property = "enabled", column = "enabled"),
		@Result(property = "createBy", column = "create_by"),
		@Result(property = "createTime", column = "create_time", jdbcType = JdbcType.TIMESTAMP)
	})
	
	/**
	 * 使用注解方式 使用 mapUnderscoreToCamelCase 自动映射
	 * @param id
	 * @return
	 */
	@Select("select id, role_name, enabled, create_by, create_time from sys_role where id = #{id}")
	Role selectById(Long id);
	
	/**
	 * 使用 resultMap 实现属性映射
	 * @param id
	 * @return
	 */

	@ResultMap("BaseResultMap")
	@Select("select id, role_name, enabled, create_by, create_time from sys_role where id = #{id}")
	Role selectById2(Long id);
	
	/**
	 * 获取全部数据
	 * @return
	 */
	@ResultMap("BaseResultMap")
	@Select("select id, role_name, enabled, create_by, create_time from sys_role")
	List<Role> selectAll();
	
	/**
	 * 不需要返回主键
	 * @param role
	 * @return
	 */
	@Insert("insert into sys_role(role_name, enabled, create_by, create_time) values(#{roleName}, #{enabled}, #{create_by}, #{createTime, jdbcType=TIMESTAMP})")
	int insert(Role role);
	
	/**
	 * 返回自增主键
	 * @param role
	 * @return
	 */
	@Insert("insert into sys_role(role_name, enabled, create_by, create_time) values(#{roleName}, #{enabled}, #{create_by}, #{createTime, jdbcType=TIMESTAMP})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert2(Role role);
	
	/**
	 * 返回非自增主键
	 * @param role
	 * @return
	 */
	@Insert("insert into sys_role(role_name, enabled, create_by, create_time) values(#{roleName}, #{enabled}, #{create_by}, #{createTime, jdbcType=TIMESTAMP})")
	//before 为 false 时值为 AFTER 为 true 时值为 BEFORE
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = Long.class)
	int insert3(Role role);
}
