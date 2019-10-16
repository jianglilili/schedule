package com.example.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

/**
 *持久化类
 */
@Entity
public class JsonStock{
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	public String a;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	@Override
	public String toString() {
		return "JsonStock [a=" + a + "]";
	}
	

}
