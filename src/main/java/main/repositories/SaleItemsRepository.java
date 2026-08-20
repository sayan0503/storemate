package main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.SaleItems;

public interface SaleItemsRepository extends JpaRepository<SaleItems, Long>{

}
