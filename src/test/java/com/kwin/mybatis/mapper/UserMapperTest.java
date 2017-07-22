package com.kwin.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kwin.mybatis.entity.Role;
import com.kwin.mybatis.entity.User;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class UserMapperTest extends BaseMapperTest {

	private SqlSession sqlSession;
	private UserMapper userMapper;

	@Test
	public void testSelectById() {
		sqlSession = this.getSqlSession();
		try {
			// 获取 mapper 接口
			userMapper = sqlSession.getMapper(UserMapper.class);
			User user = userMapper.selectById(1l);
			// user 不为空
			Assert.assertNotNull(user);
			// userName = admin
			Assert.assertEquals("admin", user.getUserName());
			System.out.println(user);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll() {
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

	@Test
	public void testSelectRolesByUserId() {
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			List<Role> roleList = userMapper.selectRolesByUserId(1l);
			// roleList 不为空
			Assert.assertNotNull(roleList);
			// 条数大于 0
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testInsert() {
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			User user = new User();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.com");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[] { 1, 2, 3 });
			user.setCreateTime(new Date());
			int row = userMapper.insert(user);
			Assert.assertEquals(1, row);
			Assert.assertNull(user.getId());
		} finally {
			// 由于默认的 sqlSessionFactory.openSession() 是不自动提交的
			// 因此不手动执行 commit 是不会提交到数据库的
			sqlSession.rollback();
			sqlSession.close();
		}
	}
}
