/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dht.repository;

import java.util.List;
import com.dht.pojo.Comment;

/**
 *
 * @author ADMIN
 */
public interface CommentRepository {
    List<Comment> getComments(int productId, int page);
    Comment addComment(String content, int productId);
}
