package main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import main.entity.User;
import main.repositories.UserRepository;

@Service
public class UserServiceAccess implements UserService{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public boolean register(User u) {
		try {
			String encodedPassword = encoder.encode(u.getPassword());
			u.setPassword(encodedPassword);
			repo.save(u);
			// 2 - 12345
			// 3 - pass123
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User login(String email, String password) {
		User u = repo.findByEmail(email);
		if(u!=null && encoder.matches(password, u.getPassword())) {
			return u;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean update(User u) {
		try{
			repo.save(u);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePassword(User u, String currentPassword) {
		User user = login(u.getEmail(), currentPassword);
		if(user!=null) {
			try {
				String endcodedPass= encoder.encode(u.getPassword());
				u.setPassword(endcodedPass);
				repo.save(u);
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
	}

}
