package com.kwin.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kwin.mybatis.entity.User;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class UserMapperTest extends BaseMapperTest {
	
	private SqlSession sqlSession;
	private UserMapper userMapper;
	
	@Test
	public void testSelectById(){
		sqlSession = this.getSqlSession();
		try {
			//获取 mapper 接口
			userMapper = sqlSession.getMapper(UserMapper.class);
			User user = userMapper.selectById(1l);
			// user 不为空
			Assert.assertNotNull(user);
			// userName = admin
			Assert.assertEquals("admin", user.getUserName());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll(){
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			List<User> userList = userMapper.selectAll();
			// userList 不为空
			Assert.assertNotNull(userList);
			// 用户数大于 0
			Assert.assertTrue(userList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}
}
