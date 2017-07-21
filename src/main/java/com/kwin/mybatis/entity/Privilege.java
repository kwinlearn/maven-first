package com.kwin.mybatis.entity;

public class Privilege {
	private Long id;				//权限id
	private String privilegeName;	//权限形成
	private String privilegeUrl;	//权限url
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getPrivilegeUrl() {
		return privilegeUrl;
	}
	public void setPrivilegeUrl(String privilegeUrl) {
		this.privilegeUrl = privilegeUrl;
	}
}
