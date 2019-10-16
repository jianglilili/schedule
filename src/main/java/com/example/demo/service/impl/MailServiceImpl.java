package com.example.demo.service.impl;

import java.net.URL;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.example.demo.mapper.MailMapper;
import com.example.demo.mapper.StockMapper;
import com.example.demo.model.JsonStock;
import com.example.demo.service.MailService;


@Service("mailService")
public class MailServiceImpl implements MailService{

	
	@Autowired
    private JavaMailSender mailSender;
	@Autowired
    private MailService mailService;
	@Autowired
	private MailMapper mailMapper;
	
	private int count=1;

    @Value("${mail.formMail.addr}")
    private String form;
    /**
     * 发送简单邮件
     * @param to 接受者
     * @param subject 主题
     * @param content 内容
     */
    //被mailSchedule（）引用的方法
	@Override
	public void sendMail(String to, String subject, String string) {
		// TODO Auto-generated method stub
		SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(form);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(string);
        try {
            mailSender.send(mailMessage);
//            System.out.println("发送简单邮件");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送简单邮件失败");
        }
	}
	
	
	

	 //用接口在浏览器查询数据的方法
     public List<JsonStock> listStock(){
    	
    	  return (List<JsonStock>)mailMapper.selectList();
     }
    	

     //发送自定义邮件的方法
     public void sendComplexMail()
     {
    	 List<JsonStock> totalList=mailService.listStock();
    	 
    		 //提取出花括号里面的内容
    		 Pattern compile=Pattern.compile("\\{([^}]*)\\}");
    		 Matcher matcher=compile.matcher(totalList.toString());
    		 while(matcher.find())
    		 {
				try {
	    			String json=matcher.group();
	    			JSONObject jsonObject=new JSONObject(json);
					if(jsonObject.getString("证券简称").equals("浦发银行"))
					{
						int count=(jsonObject.getInt("最高成交价")-jsonObject.getInt("最低成交价"))/jsonObject.getInt("最高成交价");
						if(count<=0.005)
						{
							mailService.sendMail(form,"股票行情数据",jsonObject.toString());
				            System.out.println("发送定时邮件成功");
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			 
    		 }
    		 
     }
     //schdule发送定时邮件的方法
     //发送简单邮件
     @SuppressWarnings("resource")
     public void sendSimpleMail() throws Exception
     {
        try
    	{
        	//根据symbol字段提取接口数据
		    String result2 = new Scanner(new URL("http://hq.sinajs.cn/list="+"sh600000").openStream(), "GBK").useDelimiter("\\A").next();  
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

	                	
	               String content=jsonobj.toString();
	               mailService.sendMail(form,"股票行情数据",content);
	               System.out.println("发送定时邮件成功");
	        
	    	   }catch(Exception e)
	    	   {
	    	       e.printStackTrace();
	    	   }
	    	 }
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	
	
    }
}
