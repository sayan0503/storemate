package main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.Stores;
import main.entity.User;
import main.repositories.StoreRepository;

@Service
public class StoreServiceAccess implements StoreService{

	@Autowired
	StoreRepository repo;

	@Override
	public boolean addStore(Stores s) {
		try {
			repo.save(s);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Stores> allStores(User u) {		
		return repo.findAllByUser(u);
	}

	@Override
	public Stores getStoresbyId(long id) {
		return repo.findById(id).orElse(null);
	}
	
	
}
