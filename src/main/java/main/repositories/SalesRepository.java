package main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Sales;
import main.entity.Stores;

public interface SalesRepository extends JpaRepository<Sales, Long>{

	List<Sales> findAllByStoreOrderBySaleDateDesc(Stores s);
	
	List<Sales> findAllByStoreIdOrderBySaleDateDesc(Long id);
	
	long countByStoreId(Long storeId);
	
	List<Sales> findAllByStoreId(Long id);
	void deleteAllByStoreId(long id);
}
