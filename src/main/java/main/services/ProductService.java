package main.services;

import java.util.List;

import main.entity.Products;
import main.entity.Stores;

public interface ProductService {

	public boolean addProduct(Products p);
	public List<Products> allProducts(Stores s);
	public boolean deleteProduct(Long id);
	public List<Products> getAll();
	public Products getProducts(Long id);
	public boolean updateProduct(Products p);
}
