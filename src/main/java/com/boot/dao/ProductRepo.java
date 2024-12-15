package com.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
