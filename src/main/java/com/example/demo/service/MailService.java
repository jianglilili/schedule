package com.example.demo.service;

import java.util.List;

import org.json.JSONObject;

import com.example.demo.model.JsonStock;

public interface MailService {
	
	public void sendMail(String to, String subject, String string);
    public List<JsonStock> listStock();
}
