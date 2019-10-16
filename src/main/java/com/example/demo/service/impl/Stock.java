package com.example.demo.service.impl;
/**
 * 对json内容进行解析
 */
public class Stock {

	public String success;
	public Result result;
	@Override
	public String toString()
	{
		return " "+result;
	}
}
class Result
{
	public String totline;
	public String disline;
	public String page;
	public java.util.List<Lists> lists;
	@Override
	public String toString()
	{
		return lists+"\n";
	}
}
class Lists{
	public String stoid;
	public String symbol;
	public String sname;
	@Override
	public String toString()
	{
		return symbol+"\n";
	}
}

