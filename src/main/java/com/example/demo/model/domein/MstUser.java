package com.example.demo.model.domein;

import lombok.Data;

@Data
public class MstUser {

	private int id;
	private String userName;
	private String password;
	private String fullName;
	private int isAdmin;
	
}
