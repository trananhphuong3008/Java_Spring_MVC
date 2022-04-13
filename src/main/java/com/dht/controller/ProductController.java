/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.controller;

import com.dht.pojo.Product;
import com.dht.service.CategoryService;
import com.dht.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/wedding-add")
    public String addView (Model model){
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("product", new Product());
        return "product";
    }
    
    @PostMapping("/wedding-add")
    public String addHandler (Model model,
            @ModelAttribute(value="product") Product p){
        if(this.productService.addOrUppdateProduct(p) == true)
            return "redirect:/";
        else 
            model.addAttribute("errMsg", "Something Wrong!!! Please try again");
        return "product";
    }
}
