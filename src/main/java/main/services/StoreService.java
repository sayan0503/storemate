package main.services;

import java.util.List;

import main.entity.Stores;
import main.entity.User;

public interface StoreService {

	public boolean addStore(Stores s);
	public List<Stores> allStores(User u);
	public Stores getStoresbyId(long id);
	public boolean deleteStore(Stores store);
}
