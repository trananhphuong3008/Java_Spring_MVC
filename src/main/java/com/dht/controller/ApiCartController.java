/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.controller;

import com.dht.pojo.Cart;
import com.dht.service.ReceiptService;
import com.dht.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api")
public class ApiCartController {
    @Autowired
    private ReceiptService receiptService;
    @PostMapping("/add-cart")
    public ResponseEntity <Map<String, String>>addToCart(HttpSession session,
            @RequestBody Cart c){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart == null)
            cart = new HashMap<>();
        
        if(cart.containsKey(c.getId())== true){
            Cart c1 = cart.get(c.getId());
            c1.setQuantity(c1.getQuantity() + 1);
        } else {
            cart.put(c.getId(), c);
        }
        
        session.setAttribute("cart", cart);
        
        return new ResponseEntity<>(Utils.cartStats(cart), HttpStatus.OK);
    }
    
    @PutMapping("/update-cart")
    public ResponseEntity <Map<String, String>> updateCart(
            HttpSession session,
    @RequestBody Map<String, String> params){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        int id = Integer.parseInt(params.get("id"));
        if(cart != null && cart.containsKey(id) == true){
            Cart c = cart.get(id);
            c.setQuantity(Integer.parseInt(params.get("quantity")));
            
            session.setAttribute("cart", cart);
            return new ResponseEntity<>(Utils.cartStats(cart), HttpStatus.OK);
        }
        
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/delete-cart/{productId}")
    public ResponseEntity <Map<String, String>> deleteCart(
            @ModelAttribute(name = "productId") int id,
            HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        
        if(cart != null && cart.containsKey(id)){
            cart.remove(id);
            
            session.setAttribute("cart", cart);
            return new ResponseEntity<>(Utils.cartStats(cart), HttpStatus.OK);
        }
        
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/pay")
    public ResponseEntity addPayment(HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart != null){
            if(this.receiptService.addReceipt((Map<Integer, Cart>) session.getAttribute("cart")) == true){
                session.removeAttribute("cart");
                return new ResponseEntity(HttpStatus.CREATED);
            }
        }
        
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
