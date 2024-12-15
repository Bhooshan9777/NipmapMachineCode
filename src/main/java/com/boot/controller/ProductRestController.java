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

import com.boot.dao.ProductRepo;
import com.boot.entity.Product;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/api/products")
public class ProductRestController {

	@Autowired
	public ProductRepo prepo;
	    
	@GetMapping()
	public Page<Product> getAllProducts(Pageable pageable) {
	    return prepo.findAll(pageable);
	}
	
    @PostMapping
    public String postProduct(@RequestBody Product product) {
    	System.out.println("Incoming Product: " + product);
    	prepo.save(product);
    	return"Record Added Successfully...............";
    }
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable long id){
    	
    	return prepo.findById(id);
    }
    
    @PutMapping("/{id}")
    public String updateProductById(@RequestBody Product product, @PathVariable long id) {
    	Optional<Product> existingProduct = prepo.findById(id);
	    if (existingProduct.isPresent()) {
	        Product pro = existingProduct.get();
	        pro.setName(product.getName());
	        pro.setPrice(product.getPrice());
	        prepo.save(pro);
	        return "Record Updated Successfully.............";
	    } else {
	        return "Category not found.............";
	    }
    }
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id) {
        
        if (prepo.existsById(id)) {
            
            prepo.deleteById(id);
            
            return "Record Deleted successfully...............";
        } else {
            
            return "Product not found.";
        }
    }

}