	package com.order.controller;
	import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.order.dto.ProductDTO;
import com.order.entity.Product;
import com.order.service.CategoryService;
import com.order.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/admin/products")
	public String Products(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String productsAdd(Model model) {
		model.addAttribute("ProductDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@PostMapping("/admin/products/add")
	public String PostProductsAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
			throws IOException {
		com.order.entity.Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setCategory(categoryService.updateCategoryById(productDTO.getCategoryId()).get());
		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		productService.productAdd(product);
		return "redirect:/admin/products";
	}
	
	@GetMapping("admin/products/delete/{id}")
	public String productDelete(@PathVariable long id, Model model) {
		productService.productDelete(id);
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/products/update/{id}")
	public String productUpdate(@PathVariable long id, Model model) {
		Product product = productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("ProductDTO", productDTO);
		return "productsAdd";
	}
}
