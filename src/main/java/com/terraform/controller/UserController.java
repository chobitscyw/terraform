package com.terraform.controller;

import com.terraform.model.SysUser;
import com.terraform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="v2/terraform")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome222";
	}

	@GetMapping("/users")
	public List<SysUser> getUsers() {
		return userService.findAll();
	}

	@GetMapping("/caches")
	public List<String> getCacheList() {
		return userService.getCacheListByRedis();
	}
}
