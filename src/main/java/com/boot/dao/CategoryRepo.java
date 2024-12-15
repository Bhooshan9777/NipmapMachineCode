package com.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}

