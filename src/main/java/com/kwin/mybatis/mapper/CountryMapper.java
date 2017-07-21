package com.kwin.mybatis.mapper;

import java.util.List;

import com.kwin.mybatis.entity.Country;

public interface CountryMapper {
	List<Country> selectAll();
}
