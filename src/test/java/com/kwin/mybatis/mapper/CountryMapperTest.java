package com.kwin.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kwin.mybatis.entity.Country;

public class CountryMapperTest extends BaseMapperTest{

	private SqlSession sqlSession;
	
	@Test
	public void testSelectAll() {
		//通过 sqlSessionFactory 工厂对象获取一个 SqlSession 对象
		sqlSession = this.getSqlSession();
		try {
			//调用在 mapper 中id为 selectAll 的 sql 语句
			List<Country> countryList = sqlSession.selectList("com.kwin.mybatis.mapper.CountryMapper.selectAll");
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