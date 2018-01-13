package com.project.spring_react.controllers;

import java.util.ArrayList;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.spring_react.services.UserService;
import com.project.spring_react.models.User;

@Controller
@RequestMapping("/users")
public class UserController{
	private UserService userService;

	public UserController(UserService userService){
		this.userService=userService;
	}

	@ResponseBody
	@RequestMapping("")
	public ArrayList<User> all(){
		return (ArrayList<User>)userService.all();
	}

	@PostMapping("/new")
	@ResponseBody
	public User create(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("password") String password){
		return userService.create(
			new User(name,email,password)
		);
	}

	@PostMapping("/{id}/destroy")
	@ResponseBody
	public long destroy(@RequestParam("id") long id){
		return userService.destroy(id);
	}
}
