package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.domein.MstUser;
import com.example.demo.model.form.LoginForm;
import com.example.demo.model.mapper.MstUserMapper;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	@Autowired
	MstUserMapper userMapper;
	
	@PostMapping("/user")
	public String userLogin(LoginForm form, Model model) {
		
		MstUser user =  userMapper.findByUserNameAndPassword(form);
		if(user == null || user.getIsAdmin() == 1) {
			model.addAttribute("errUserMessage", "存在しないユーザーです。");
			model.addAttribute("userName", form.getUserName());
			return "login";
		}
		
		return "userIndex";
	}
	
	@PostMapping("/admin")
	public String adminLogin(LoginForm form, Model model) {
		
		MstUser user =  userMapper.findByUserNameAndPassword(form);
		if(user == null) {
			model.addAttribute("errAdminMessage", "存在しない管理者です。");
			model.addAttribute("userName", form.getUserName());
			return "login";
		} else if(user.getIsAdmin() == 0) {
			model.addAttribute("errAdminMessage", "管理者ではありません。");
			model.addAttribute("userName", form.getUserName());
			return "login";
		}
		
		return "userIndex";
	}
}
