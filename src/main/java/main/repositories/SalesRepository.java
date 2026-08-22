package main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Sales;
import main.entity.Stores;

public interface SalesRepository extends JpaRepository<Sales, Long>{

	List<Sales> findAllByStoreOrderBySaleDateDesc(Stores s);
}
