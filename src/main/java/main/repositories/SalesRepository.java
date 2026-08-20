package main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long>{

}
