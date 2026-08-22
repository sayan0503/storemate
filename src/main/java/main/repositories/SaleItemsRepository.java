package main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.SaleItems;
import main.entity.Sales;


public interface SaleItemsRepository extends JpaRepository<SaleItems, Long>{

	List<SaleItems> findAllBySale(Sales sale);
}
