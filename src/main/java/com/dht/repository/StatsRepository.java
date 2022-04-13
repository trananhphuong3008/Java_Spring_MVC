/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dht.repository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface StatsRepository {
    List<Object[]> countCates();
    List<Object[]> productRevenue(String kw, Date fromDate, Date toDate);       
}
