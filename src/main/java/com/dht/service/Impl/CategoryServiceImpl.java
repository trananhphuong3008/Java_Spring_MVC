/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.service.Impl;

import com.dht.pojo.Category;
import com.dht.service.CategoryService;
import java.util.List;
import org.springframework.stereotype.Service;
import com.dht.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ADMIN
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.getCategories();
    }
    
}
