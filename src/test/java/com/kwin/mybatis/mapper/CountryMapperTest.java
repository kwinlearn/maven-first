package com.kwin.mybatis.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kwin.mybatis.entity.Country;

public class CountryMapperTest {
	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void init() {
		try {
			//通过 Resources 工具类将 mybatis-config.xml 文件读入 Reader 对象
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			//使用 SqlSessionFactroyBuilder 构建类构建 SqlSessionFactory 工厂对象
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectAll() {
		//通过 sqlSessionFactory 工厂对象获取一个 SqlSession 对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//调用在 mapper 中id为 selectAll 的 sql 语句
			List<Country> countryList = sqlSession.selectList("selectAll");
			printCountryList(countryList);
		} finally {
			//最终关闭 sqlSession
			sqlSession.close();
		}
		
	}
	
	private void printCountryList(List<Country> countryList){
		for (Country country : countryList) {
			System.out.printf("%-4d%4s%4s\n", country.getId(),country.getCountryName(),country.getCountryCode());
		}
	}
}