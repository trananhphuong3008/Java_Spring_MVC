/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.service.Impl;

import com.dht.pojo.Comment;
import com.dht.repository.CommentRepository;
import com.dht.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getComments(int productId, int page) {
        return this.commentRepository.getComments(productId, page);
    }

    @Override
    public Comment addComment(String content, int productId) {
        return this.commentRepository.addComment(content, productId);
    }
    
}
