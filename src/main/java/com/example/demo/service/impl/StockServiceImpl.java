package com.example.demo.service.impl;





import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.example.demo.mapper.StockMapper;
import com.example.demo.service.StockService;

@Service
public class StockServiceImpl implements StockService{
	@Autowired
    private StockMapper stockMapper;
    public void insertValues1(){
      try
      {
    	String json = new Scanner(new URL("http://api.k780.com/?app=finance.stock_list&category=hs&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json").openStream(), "GBK").useDelimiter("\\A").next();  
    	Stock stock=new Stock();
		JSONObject obj=new JSONObject(json);
		String success=obj.getString("success");
		stock.success=success;
		
		JSONObject obj1=obj.getJSONObject("result");
		Result result=new Result();
		result.totline=obj1.getString("totline");
		result.disline=obj1.getString("disline");
		result.page=obj1.getString("page");
		stock.result=result;
		
		List<Lists> lists=new ArrayList<>();
		JSONArray jArr=obj1.getJSONArray("lists");
		for(int i=0;i<jArr.length();i++)
		{
			JSONObject obj2=jArr.getJSONObject(i);
			Lists list=new Lists();
			list.symbol=obj2.getString("symbol");
			lists.add(list);
			
			
	            //根据symbol字段提取接口数据
			    String result2 = new Scanner(new URL("http://hq.sinajs.cn/list="+list.symbol).openStream(), "GBK").useDelimiter("\\A").next();  
	               //根据接口数据提取出字符串双引号里面内容
	               Pattern p1=Pattern.compile("\"(.*?)\"");
	    		   Matcher m = p1.matcher(result2);
	    		   while (m.find()) {
	    			   String str01=m.group().trim().replace("\"","")+" ";
	    			   try {
	    			   //把str01按逗号分割
	    		       String b[]=str01.split(",");
	    		       Map<String,String> map=new LinkedHashMap<String,String>();
	    		       map.put("证券简称", b[0]);
	    		       map.put("今日开盘价", b[1]);
	    		       map.put("昨日收盘价", b[2]);
	    		       map.put("最近成交价", b[3]);
	    		       map.put("最高成交价", b[4]);
	    		       map.put("最低成交价", b[5]);
	    		       map.put("买入价", b[6]);
	    		       map.put("卖出价", b[7]);
	    		       map.put("成交数量", b[8]);
	    		       map.put("成交金额", b[9]);
	    		       map.put("买数量一", b[10]);
	    		       map.put("买价位一", b[11]);
	    		       map.put("买数量二", b[12]);
	    		       map.put("买价位二", b[13]);
	    		       map.put("买数量三", b[14]);
	    		       map.put("买价位三", b[15]);
	    		       map.put("买数量四", b[16]);
	    		       map.put("买价位四", b[17]);
	    		       map.put("买数量五", b[18]);
	    		       map.put("买价位五", b[19]);
	    		       map.put("卖数量一", b[20]);
	    		       map.put("卖价位一", b[21]);
	    		       map.put("卖数量二", b[22]);
	    		       map.put("卖价位二", b[23]);
	    		       map.put("卖数量三", b[24]);
	    		       map.put("卖价位三", b[25]);
	    		       map.put("卖数量三", b[26]);
	    		       map.put("卖价位四", b[27]);
	    		       map.put("卖数量五", b[28]);
	    		       map.put("卖价位五", b[29]);
	    		       map.put("行情日期", b[30]);
	    		       map.put("行情时间", b[31]);
	    		       map.put("停牌状态", b[32]);
	    		       com.alibaba.fastjson.JSONObject jsonobj=com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(map),Feature.OrderedField);
	    		       
	    		       stockMapper.insert(jsonobj.toString());
	    			   }catch(Exception e)
	    			   {
	    				   e.printStackTrace();
	    			   }
                       
	    		   }
	    		   
	    		       
	    }
	            // 释放资源
		
		result.lists=lists;
//		String str=stock.toString();
//		String str01=str.replace(",", " ");
//		return getJsonStock();
    	
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        
    }
	

}
