package com.boot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dao.CategoryRepo;
import com.boot.entity.Category;


@RestController
@RequestMapping("/api/categories")

public class CategoryRestController {

	@Autowired
	public CategoryRepo crepo;

	@GetMapping()
	public Page<Category> getAllCategories(Pageable pageable) {
	    return crepo.findAll(pageable);
	}

	@PostMapping()
	public String postCategory(@RequestBody Category category) {
		crepo.save(category);
		return "Record Added SuccessFully.............";
	}
	
	@GetMapping("/{id}")
	public Optional<Category> getCategoryById(@PathVariable long id) {
	    return crepo.findById(id);
	}

	
	@PutMapping("/{id}")
	public String putCategory(@PathVariable long id, @RequestBody Category category) {
	    Optional<Category> existingCategory = crepo.findById(id);
	    if (existingCategory.isPresent()) {
	        Category cate = existingCategory.get();
	        cate.setName(category.getName());
	        cate.setDescription(category.getDescription());
	        crepo.save(cate);
	        return "Record Updated Successfully.............";
	    } else {
	        return "Category not found.............";
	    }
	}

	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable long id) {
		if (crepo.existsById(id)) {
		    crepo.deleteById(id);
		    return "Data deleted successfully.";
		} else {
		    return "Category not found.";
		}
	}
}
