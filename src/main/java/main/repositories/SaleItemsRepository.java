package main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.entity.SaleItems;
import main.entity.Sales;
import main.entity.Stores;
import main.entity.TopSellingProduct;


public interface SaleItemsRepository extends JpaRepository<SaleItems, Long>{

	List<SaleItems> findAllBySale(Sales sale);
	
	List<SaleItems> findAllBySale_StoreId(Long storeId);
	
	@Query("""
			 SELECT new main.entity.TopSellingProduct(
            p.name,
            SUM(si.quantity),
            SUM(si.subTotal)
	        )
	        FROM SaleItems si
	        JOIN si.product p
	        WHERE p.store = :store
	        GROUP BY p.id, p.name
	        ORDER BY SUM(si.quantity) DESC
			""")
	List<TopSellingProduct> findTopSellingProducts(@Param("store") Stores store);
	
	void deleteBySaleId(Long id);
}
