package main.services;

import main.entity.User;

public interface UserService{

	public boolean register(User u);
	public User login(String email, String password);
	public boolean update(User u);
	public boolean updatePassword(User u, String currentPassword);
}
