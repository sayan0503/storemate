package main.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Products;
import main.entity.Stores;

public interface ProductRepository extends JpaRepository<Products, Long>{

	List<Products> findAllByStore(Stores s);
	Optional findById(Long id);
	void deleteAllByStoreId(long id);
}
