package main.services;

import java.util.List;

import main.entity.Products;
import main.entity.Stores;

public interface ProductService {

	public boolean addProduct(Products p);
	public List<Products> allProducts(Stores s);
}
