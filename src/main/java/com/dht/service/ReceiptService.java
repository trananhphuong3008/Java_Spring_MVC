/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dht.service;

import com.dht.pojo.Cart;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public interface ReceiptService {
    public boolean addReceipt(Map<Integer, Cart> cart);
}
