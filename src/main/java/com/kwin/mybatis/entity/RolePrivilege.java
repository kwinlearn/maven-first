package com.kwin.mybatis.entity;

public class RolePrivilege {
	private Long roleId;		//角色id
	private Long privilegeId;	//权限id
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPrivilege() {
		return privilegeId;
	}
	public void setPrivilege(Long privilege) {
		this.privilegeId = privilege;
	}
}
