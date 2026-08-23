package main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import main.entity.Sales;
import main.entity.Stores;
import main.entity.User;
import main.repositories.ProductRepository;
import main.repositories.SaleItemsRepository;
import main.repositories.SalesRepository;
import main.repositories.StoreRepository;

@Service
public class StoreServiceAccess implements StoreService{

	@Autowired
	private StoreRepository repo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SalesRepository salesRepo;
	
	@Autowired
	private SaleItemsRepository itemRepo;
	
	
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

	@Override
	@Transactional
	public boolean deleteStore(Stores store) {
		try {
			List<Sales> allSale = salesRepo.findAllByStoreId(store.getId());
			for(Sales sale: allSale) {
				itemRepo.deleteBySaleId(sale.getId());
			}
			salesRepo.deleteAllByStoreId(store.getId());
			productRepo.deleteAllByStoreId(store.getId());
			repo.deleteById(store.getId());
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
