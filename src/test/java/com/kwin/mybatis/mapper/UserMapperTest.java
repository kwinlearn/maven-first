package com.kwin.mybatis.mapper;

import java.util.ArrayList;
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

	@Test
	public void testInsert2() {
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
			int row = userMapper.insert2(user);
			Assert.assertEquals(1, row);
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testInser3() {
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
			int row = userMapper.insert3(user);
			Assert.assertEquals(1, row);
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testUpdateById() {
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			User user = userMapper.selectById(1l);
			Assert.assertEquals("admin", user.getUserName());
			user.setUserName("admin_test"); // 修改用户名
			user.setUserEmail("test@mybatis.com"); // 修改用户邮箱
			int row = userMapper.updateById(user);
			Assert.assertEquals(1, row);
			user = userMapper.selectById(1l);
			Assert.assertEquals("admin_test", user.getUserName());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testDeleteById() {
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			User user = userMapper.selectById(1l);
			Assert.assertNotNull(user);
			int row = userMapper.deleteById(user.getId());
			Assert.assertEquals(1, row);
			user = userMapper.selectById(1l);
			Assert.assertNull(user);
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserIdAndRoleEnabled() {
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			List<Role> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1l, 1);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectByUser() {
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			User conditions = new User();
			conditions.setUserName("ad");
			List<User> userList = userMapper.selectByUser(conditions);
			Assert.assertTrue(userList.size() > 0);
			conditions = new User();
			conditions.setUserEmail("test@mybatis.com");
			userList = userMapper.selectByUser(conditions);
			Assert.assertTrue(userList.size() > 0);
			conditions = new User();
			conditions.setUserName("ad");
			conditions.setUserEmail("test@mybatis.com");
			userList = userMapper.selectByUser(conditions);
			Assert.assertTrue(userList.size() == 0);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByIdSelective(){
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			User user = new User();
			user.setId(1l);
			user.setUserEmail("test@mybatis.com");
			int row = userMapper.updateByIdSelective(user);
			Assert.assertEquals(1, row);
			user = userMapper.selectById(1l);
			Assert.assertEquals("admin", user.getUserName());
			Assert.assertEquals("test@mybatis.com", user.getUserEmail());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectByIdOrUserName(){
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			User userQuery = new User();
			userQuery.setId(1l);
			userQuery.setUserName("admin");
			User user = userMapper.selectByIdOrUserName(userQuery);
			Assert.assertNotNull(user);
			// 当没有 id 时
			userQuery.setId(null);
			user = userMapper.selectByIdOrUserName(userQuery);
			Assert.assertNotNull(user);
			// 当 userName 和 id 都为null 时
			userQuery.setUserName(null);
			user = userMapper.selectByIdOrUserName(userQuery);
			Assert.assertNull(user);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectByIdList(){
		sqlSession = this.getSqlSession();
		try {
			userMapper = sqlSession.getMapper(UserMapper.class);
			List<Long> idList = new ArrayList<Long>();
			idList.add(1l);
			idList.add(1001l);
			List<User> userList = userMapper.selectByIdList(idList);
			Assert.assertEquals(2, userList.size());
		} finally {
			sqlSession.close();
		}
	}
}
