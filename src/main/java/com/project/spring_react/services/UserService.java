package com.project.spring_react.services;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.spring_react.repositories.UserRepository;
import com.project.spring_react.models.User;

@Service
public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository=userRepository;
	}
		
	public User create(User user){
		return userRepository.save(user);
	}

	public User findById(long id){
		return (User)userRepository.findOne(id);
	}

	public ArrayList<User> all(){
		return (ArrayList<User>)userRepository.findAll();
	}

	public void update(User user){
		userRepository.save(user);
	}

	public long destroy(long id){
		userRepository.delete(id);
		return id;
	}
}
