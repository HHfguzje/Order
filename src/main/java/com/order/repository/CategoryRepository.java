package com.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findAll();
}
