package com.hsbc.dao;

import java.util.List;

import com.hsbc.entity.User;

public interface UserDao {

	List<User> findAllDevelopers();
	List<User> findAllTesters();
	List<User> findAllUsers();

}