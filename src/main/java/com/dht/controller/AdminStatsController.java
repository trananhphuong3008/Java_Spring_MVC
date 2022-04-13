/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.controller;

import com.dht.service.StatsService;
import javax.ws.rs.DefaultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("/admin")
public class AdminStatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/cates-stats")
    public String catesStats(Model model) {
        model.addAttribute("stats", this.statsService.countCates());
        return "cates-stats";
    }

    @GetMapping("/revenue-stats")
    public String revenueStats(Model model,
            @RequestParam(name = "kw", defaultValue = "") String kw,
            @RequestParam(name = "fromDate", defaultValue = "") String fromDate,
            @RequestParam(name = "toDate", defaultValue = "") String toDate
    ) {
        model.addAttribute("stats", this.statsService.productRevenue(kw, fromDate, toDate));
        return "revenue-stats";
    }
}
