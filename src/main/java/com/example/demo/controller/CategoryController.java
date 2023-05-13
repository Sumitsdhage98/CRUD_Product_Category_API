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

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repo.CategoryRepository;

@RestController
public class CategoryController {

	
	@Autowired
	private CategoryRepository catrepo;
	
	
	@GetMapping("/api/categories")
	public List<Category> getAllCategories(){
		return catrepo.findAll();
	}
	
	@PostMapping("/api/categories")
	public List<Category> createNewCategories(@RequestBody Category category)
	{
		List<Category> ca = new ArrayList<>();
		ca.add(category);
		catrepo.saveAll(ca);
		return ca;
	}
	
	@GetMapping("/api/categories/{id}")
	public Optional<Category> findByCategoryId(@PathVariable("id") long id)
	{
		return catrepo.findById(id);
	}
	
	@DeleteMapping("/api/categories/{id}")
	public void deleteCategory(@PathVariable("id") long id)
	{
		catrepo.deleteById(id);
	}
	
	
	@PutMapping("/api/categories/{id}")
	public void updateCategory(@PathVariable("id") long id, @RequestBody Category newcategories)
	{
		Optional<Category> up = catrepo.findById(id);
		
		if(up.isPresent())
		{
			Category cate = up.get();
			cate.setName(newcategories.getName());
		
			catrepo.save(cate);
		}
		
	}
	
}
