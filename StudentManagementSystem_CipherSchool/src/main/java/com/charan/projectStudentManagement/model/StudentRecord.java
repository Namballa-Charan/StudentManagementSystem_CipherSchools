package com.charan.projectStudentManagement.model;

import org.springframework.stereotype.Component;

@Component
public class StudentRecord {

	private int rollNo;
	private String name;
	private long contactNo;
	
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int i) {
		this.rollNo = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long d) {
		this.contactNo = d;
	}
	
}
