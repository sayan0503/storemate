package main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Stores;
import main.entity.User;

public interface StoreRepository extends JpaRepository<Stores, Long>{

	List<Stores> findAllByUser(User u);
}
