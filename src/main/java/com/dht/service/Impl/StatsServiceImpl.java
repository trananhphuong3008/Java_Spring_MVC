/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.service.Impl;

import com.dht.repository.StatsRepository;
import com.dht.service.StatsService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public List<Object[]> countCates() {
        return this.statsRepository.countCates();
    }

    @Override
    public List<Object[]> productRevenue(String kw, String fromDate, String toDate) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fd = null;
        Date td = null;
        if (fromDate != null && !fromDate.isEmpty())
             try {
            fd = f.parse(fromDate);
        } catch (ParseException ex) {
            Logger.getLogger(StatsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (toDate != null && !toDate.isEmpty())
            try {
            td = f.parse(toDate);
        } catch (ParseException ex) {
            Logger.getLogger(StatsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.statsRepository.productRevenue(kw, td, td);
    }

}
