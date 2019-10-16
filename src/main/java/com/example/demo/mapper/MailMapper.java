package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.JsonStock;

@Mapper
public interface MailMapper {

	//查询
		List<JsonStock> selectList();
}
