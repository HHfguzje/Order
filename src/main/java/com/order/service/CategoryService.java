package com.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Category;
import com.order.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	public void delCategoryById(int id) {
		categoryRepository.deleteById(id);
	}
	
	public Optional<Category> updateCategoryById(int id){
		return categoryRepository.findById(id);
	}
}
