/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dht.service;

import com.dht.pojo.Comment;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface CommentService {

    List<Comment> getComments(int productId, int page);

    Comment addComment(String content, int productId);
}
