package com.kwin.mybatis.mapper;

import java.util.List;

import com.kwin.mybatis.entity.Country;

public interface CountryMapper {
	/**
	 * 查询所有country
	 * @return
	 */
	List<Country> selectAll();
}
