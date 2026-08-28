package main.repositories;

import java.math.BigDecimal;
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
	
	@Query("""
			SELECT COALESCE(SUM(si.quantity),0)
			FROM SaleItems si
			WHERE si.product.id=:productId
			""")
	Long getTotalSoldByProduct(@Param("productId") Long productId);
	
	 @Query("""
		        SELECT COALESCE(SUM(si.subTotal), 0)
		        FROM SaleItems si
		        WHERE si.sale.store IN :stores
		    """)
	BigDecimal getTotalRevenue(@Param("stores") List<Stores> stores);


	 @Query("""
		        SELECT COALESCE(SUM(
		            (si.price - si.costPrice) * si.quantity
		        ), 0)
		        FROM SaleItems si
		        WHERE si.sale.store IN :stores
		    """)
	BigDecimal getTotalProfit(@Param("stores") List<Stores> stores);


	@Query("""
		        SELECT new main.entity.TopSellingProduct(
		            p.name,
		            SUM(si.quantity),
		            SUM(si.subTotal)
		        )
		        FROM SaleItems si
		        JOIN si.product p
		        WHERE si.sale.store IN :stores
		        GROUP BY p.id, p.name
		        ORDER BY SUM(si.quantity) DESC
		    """)
	List<TopSellingProduct> findMostSellingProduct( @Param("stores") List<Stores> stores);

	@Query("""
		        SELECT COALESCE(SUM(si.subTotal), 0)
		        FROM SaleItems si
		        WHERE si.sale.store = :store
		    """)
	BigDecimal getRevenueByStore( @Param("store") Stores store);


	 @Query("""
		        SELECT COALESCE(SUM(
		            (si.price - si.costPrice) * si.quantity
		        ), 0)
		        FROM SaleItems si
		        WHERE si.sale.store = :store
		    """)
	BigDecimal getProfitByStore(  @Param("store") Stores store);
}
