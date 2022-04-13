/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.controller;

import java.util.List;
import com.dht.pojo.Comment;
import com.dht.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/api")
public class ApiCommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/products/{productId}/comments")
    public ResponseEntity<List<Comment>> getComments(
            @PathVariable(name ="productId") int productId,
            @RequestParam(name ="page", defaultValue = "1") Integer page){
        List<Comment> comments = this.commentService.getComments(productId, page);
        
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
