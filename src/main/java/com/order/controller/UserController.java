package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.service.CategoryService;
import com.order.service.ProductService;

@Controller
public class UserController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/", "/index"})
	public String Index() {
		return "index";
	}
	@GetMapping("/order")
	public String Oder(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "order";
	}
	@GetMapping("/order/category/{id}")
	public String foodbycategory(@PathVariable int id, Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		return "order";
	}
	@GetMapping("/order/viewproduct/{id}")
	public String productById(Model model, @PathVariable long id) {
		model.addAttribute("products", productService.getProductById(id).get());
		return "viewProduct";
	}
	@GetMapping("/addToCart/{id}")
	public String addToCart(Model model, @PathVariable long id) {
		model.addAttribute("products", productService.getProductById(id).get());
		return "cart";
	}
}
