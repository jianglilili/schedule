package com.example.demo.controller;



import javax.annotation.Resource;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JsonStock;
import com.example.demo.service.impl.StockServiceImpl;

@RequestMapping("/stock")
@RestController
public class StockController {
    @Resource 
    private  StockServiceImpl stockServiceImpl;
    @GetMapping("/insertValues")
    public void insertValus() throws Exception {
    	
//        InsertID insertID=new InsertID();
    	//进行插入数据库操作	
//    	JsonStock jsonStock=new JsonStock();
//    	jsonStock.setId(insertID.insertValues2());
//    	stockServiceImpl.insertValues2(jsonStock);
    	//插入json数据
        stockServiceImpl.insertValues1();
		
    	
    }
    
    
}
