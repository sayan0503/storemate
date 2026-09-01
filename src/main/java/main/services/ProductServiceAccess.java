package main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.Products;
import main.entity.Stores;
import main.repositories.ProductRepository;

@Service
public class ProductServiceAccess implements ProductService{

	@Autowired
	private ProductRepository repo;
	
	@Override
	public boolean addProduct(Products p) {
		try {
			repo.save(p);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Products> allProducts(Stores s) {
		return repo.findAllByStore(s);
	}

	@Override
	public boolean deleteProduct(Long id) {
		try {
			repo.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Products> getAll() {
		return repo.findAll();
	}

	@Override
	public Products getProducts(Long id) {
		return (Products) repo.findById(id).orElse(null);
	}

	@Override
	public boolean updateProduct(Products p) {
		try {
			repo.save(p);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Products> getAllProductsFromAllStores(List<Stores> stores) {
		return repo.findAllByStoreIn(stores);
	}

}
