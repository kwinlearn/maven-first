package com.kwin.mybatis.entity;

public class UserRole {
	private Long userId;	//用户id
	private Long roleId;	//角色id
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
