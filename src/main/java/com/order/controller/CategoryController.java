package com.order.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.order.entity.Category;
import com.order.service.CategoryService;
import com.order.service.ProductService;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping("/admin")
	public String admin() {
		return "adminHome";
	}
	
	//Phan loai san pham
	@GetMapping("/admin/categories")
	public String Categories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
		//them loai san pham
		@GetMapping("admin/categories/add")
		public String categoryAdd(Model model) {
			model.addAttribute("category", new Category());
			return "categoryAdd";
		}
		@PostMapping("/admin/categories/add")
		public String saveCategory(@ModelAttribute("category") Category category) {
			categoryService.addCategory(category);
			return "redirect:/admin/categories";
		}
		
		//xoa loai san pham
		@GetMapping("/admin/categories/delete/{id}")
			public String categoryDelete(@PathVariable int id) {
				categoryService.delCategoryById(id);
				return "redirect:/admin/categories";
		}
		//cap nhat san pham
		@GetMapping("/admin/categories/update/{id}")
		public String categoryUpdate(@PathVariable int id, Model model) {
			Optional<Category> category = categoryService.updateCategoryById(id);
			if(category.isPresent()) {
				model.addAttribute("category", category.get());
			} else
				return "404 error";
			return "categoryAdd";
		}
	
}
