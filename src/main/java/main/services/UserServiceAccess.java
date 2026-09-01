package main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.entity.Sales;
import main.entity.Stores;
import main.entity.User;
import main.repositories.ProductRepository;
import main.repositories.SaleItemsRepository;
import main.repositories.SalesRepository;
import main.repositories.StoreRepository;
import main.repositories.UserRepository;

@Service
public class UserServiceAccess implements UserService{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SalesRepository saleRepo;
	
	@Autowired
	private SaleItemsRepository itemRepo;
	
	@Autowired
	private StoreRepository storeRepo;
	
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

	@Override
	@Transactional
	public boolean deleteUser(User user) {
		try{
			List<Stores> stores = storeRepo.findAllByUser(user);
			for(Stores store: stores) {
				productRepo.deleteAllByStoreId(store.getId());
				List<Sales> sales = saleRepo.findAllByStoreId(store.getId());
				for(Sales sale: sales) {
					itemRepo.deleteBySaleId(sale.getId());
				}
				saleRepo.deleteAllByStoreId(store.getId());
				storeRepo.deleteById(store.getId());
			}
			repo.delete(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
