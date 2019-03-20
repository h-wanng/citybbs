package com.city.bbs.service;

import java.io.InputStream;
import java.util.List;

import com.city.bbs.model.UserModel;

public interface IUserService {
	public void addUser(UserModel um) throws Exception;

	public void updateUsers(UserModel um) throws Exception;

	public UserModel getUserById(int id) throws Exception;
	
	public UserModel getUserByName(String username) throws Exception;

	public void deleteUser(UserModel um);

	public List<UserModel> getAllList();

	public List<UserModel> getByPage(int page, int rows);

	public int getUserCount();

	public int loginCheck(String name, String password) throws Exception;

	public int login(String name, String password) throws Exception;
	
	public InputStream getUserPhoto(int uid) throws Exception;
}	
