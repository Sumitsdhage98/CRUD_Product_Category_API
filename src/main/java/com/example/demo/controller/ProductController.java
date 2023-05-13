package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepository;

@RestController
public class ProductController {

	
	@Autowired
	private ProductRepository prorepo;
	
	@GetMapping("/api/products")
	public List<Product> getAllproducts()
	{
		return prorepo.findAll();
	}
	
	@PostMapping("/api/products")
	public List<Product> createNewProducts(@RequestBody Product products)
	{
		List<Product> pro = new ArrayList<>();
		pro.add(products);
		prorepo.saveAll(pro);
		return pro;
	}
	
	@GetMapping("/api/products/{id}")
	public Optional<Product> findByProductId(@PathVariable("id") long id)
	{
		return prorepo.findById(id);
	}
	
	@DeleteMapping("/api/products/{id}")
	public void deleteProduct(@PathVariable("id") long id)
	{
		prorepo.deleteById(id);
	}
	
	@PutMapping("/api/products/{id}")
	public void updateProduct(@PathVariable("id") long id, @RequestBody Product newproducts)
	{
		Optional<Product> up = prorepo.findById(id);
		
		if(up.isPresent())
		{
			Product prod = up.get();
			prod.setBrand(newproducts.getBrand());
		
			prorepo.save(prod);
		}
		
	}
	
	
	
	
	
}
