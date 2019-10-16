package com.example.demo.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.JsonStock;


@Mapper
public interface StockMapper {


    /**
     * 
        * @Title: insert 
        * @Description: 插入数据 
        * @param @param record
        * @param @return    参数
        * @return int    返回类型
        * @throws
        * @author wjk
     */
	
	//插入id值
//    int insert(JsonStock jsonStock);
    //插入json值
	void insert(String string);


}
