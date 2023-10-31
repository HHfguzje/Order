package com.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findAll();

	List<Product> findAllByCategory_Id(int id);
}
