package main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
