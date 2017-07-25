package com.kwin.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kwin.mybatis.entity.Role;

import junit.framework.Assert;

public class RoleMapperTest extends BaseMapperTest {
	private SqlSession sqlSession;
	private RoleMapper roleMapper;
	
	@Test
	public void testSelectById(){
		sqlSession = this.getSqlSession();
		try {
			roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.selectById(1l);
			Assert.assertNotNull(role);
			Assert.assertEquals("管理员", role.getRoleName());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectById2(){
		sqlSession = this.getSqlSession();
		try {
			roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.selectById2(1l);
			Assert.assertNotNull(role);
			Assert.assertEquals("管理员", role.getRoleName());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll(){
		sqlSession = this.getSqlSession();
		try {
			roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<Role> roleList = roleMapper.selectAll();
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size()>0);
			Assert.assertNotNull(roleList.get(0).getRoleName());
		} finally {
			sqlSession.close();
		}
	}
	
}
