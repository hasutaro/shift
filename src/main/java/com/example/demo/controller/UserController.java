package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.domein.MstUser;
import com.example.demo.model.form.RegistForm;
import com.example.demo.model.mapper.MstUserMapper;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	MstUserMapper userMapper;
	
	@GetMapping("/toRegist")
	public String toRegist() {
		
		return "regist";
	}
	
	@PostMapping("/regist")
	public String regist(RegistForm form, Model model) {
		
		String errMessage = null;
		String url = null;
		
		MstUser nUser = userMapper.findByUserName(form);
		MstUser pUser = userMapper.findByUserPassword(form);
		
		if(form.getFullName().isEmpty() || form.getPassword().isEmpty() || form.getUserName().isEmpty()) {
			errMessage = "全て必須項目です。";
			url = "regist";
		} else if(nUser != null) {
			errMessage = "このユーザー名は既に使用されています。";
			url = "regist";
		} else if(pUser != null) {
			errMessage = "このパスワードは既に使用されています。";
			url = "regist";
		} else {
			userMapper.save(form);
			model.addAttribute("message", "登録しました。");
			return "login";
		}
		
		model.addAttribute("errMessage", errMessage);
		model.addAttribute("userName", form.getUserName());
		model.addAttribute("fullName", form.getFullName());
		
		return url;
	}
	
	@GetMapping("/toShiftRequest")
	public String toShiftRequest() {
		
		
		
		return "shiftRequest";
	}

}
