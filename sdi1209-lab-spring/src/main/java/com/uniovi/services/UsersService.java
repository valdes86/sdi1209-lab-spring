/**
 * 
 */
package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */
@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
}
