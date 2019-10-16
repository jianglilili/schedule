package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JsonStock;
import com.example.demo.service.MailService;
import com.example.demo.service.impl.MailServiceImpl;

@RequestMapping("/stock")
@RestController
public class SelectController {

	@Resource
	private MailServiceImpl mailServiceImpl;
	
	@GetMapping("/list")
	public List<JsonStock> listStock()
	{
		return mailServiceImpl.listStock();
	}
}
