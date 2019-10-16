package com.example.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONException;
import com.example.demo.service.impl.MailServiceImpl;

@RequestMapping("/stock")
@RestController
public class MailController {
	
	@Resource
	private MailServiceImpl mailServiceImpl;
	@GetMapping("/mail")
	public void sendMail()
	{
		mailServiceImpl.sendComplexMail();
	}

}
