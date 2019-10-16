package com.example.demo.schedule;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.example.demo.service.MailService;
import com.example.demo.service.impl.StockServiceImpl;

/**
 *每天插入数据库操作 
 */
@MapperScan("com.example.demo.mapper")
@Component
public class StockSchedule {
	/**
	 * 表示每天下午三点执行股票插入任务
	 */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Resource
	private StockServiceImpl stockServiceImpl;
//	@Scheduled(cron="0 */5 * * * ?")
	@Scheduled(cron="0 0 15 * * ?")
	public void run()
	{
		System.out.println("从2019-09-16起开始执行任务！"+"现在插入数据的时间是："+dateFormat.format(new Date()));
		stockServiceImpl.insertValues1();
	}
}
