/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.controller;

import com.cloudinary.Cloudinary;
import com.dht.pojo.Cart;
import com.dht.pojo.Category;
import com.dht.service.CategoryService;
import com.dht.service.ProductService;
import com.dht.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @ModelAttribute
    public void commonAttributes(Model model, HttpSession session){
      model.addAttribute("categories", this.categoryService.getCategories()); 
      model.addAttribute("cartStats", Utils.cartStats((Map<Integer, Cart>) session.getAttribute("cart")));
    }
    
    @RequestMapping("/")
    public String index(Model model,
            @RequestParam(name= "kw", required = false) String kw,
            @RequestParam(name= "categoryId", required = false, defaultValue = "") String categoryId,
            @RequestParam(name= "page", defaultValue = "1")Integer page){
        
        Map<String, String> params = new HashMap<>();
        params.put("kw", kw);
        params.put("categoryId", categoryId);
        model.addAttribute("products", this.productService.getProducts(params, page));
        model.addAttribute("productCounter", this.productService.countProduct());
//        List<String> cates = new ArrayList<>();
//        cates.add("Mobile");
//        cates.add("Tablet");
//        cates.add("Desktop");
//        cates.add("Laptop");       
//        Session session = sessionFactory.getObject().getCurrentSession();
//        Query q = session.createQuery("From Category");
//        List<Category> cates = q.getResultList();
//        //session.close();
//        model.addAttribute("name", "Nguyen Van A");
//        model.addAttribute("categories", cates);
 //       model.addAttribute("user", new User());
        return "index";
    }
    
    @RequestMapping("/products/{productId}")
    public String productDetails(Model model,
            @PathVariable(name="productId") int id){
        model.addAttribute("product", this.productService.getProductById(id));
        return "product-detail";
    }
//    @PostMapping("/upload")
//    public String upload (@ModelAttribute(value = "user")User user){
  //      try {
    //        Map res = this.cloudinary.uploader().upload(user.getAvatar().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
  //          System.out.println(res); //secure_url
    //    } catch (IOException ex) {
 //           Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
   //     }
     //   return "redirect:/login?path=test";
    //}
    
//    @RequestMapping("/hello/{name}")
//    public String Test (Model model,
//            @PathVariable(name = "name") String name){
//        model.addAttribute("name", name);
//        return "index";
//    }
    
//    @RequestMapping("/hello2")
//    public String hello2(Model model,
//            @RequestParam(required = false) Map<String, String> params){
//        String fn = params.get("firstName");
//        String ln = params.get("lastName");
//        model.addAttribute("name", fn + " " + ln);
//        return "index";
//    }
    
//    @GetMapping("/login")
//    public String loginView(Model model){
//        model.addAttribute("user", new User());
//        return "login";
//    }
    
//    @PostMapping("/login")
//    public String loginHandler(Model model,
//            @ModelAttribute(value = "user")User user){
//        if(user.getUsername().equals("admin") && user.getPassword().equals("123"))
//            model.addAttribute("msg", "SUCCESSFULL");
//        else
//            model.addAttribute("msg", "FAILED");
//        return "login";
//    }
    
    
}
