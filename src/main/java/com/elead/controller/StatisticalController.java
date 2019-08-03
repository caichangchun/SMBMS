package com.elead.controller;


import com.elead.pojo.SmbmsStatistical;
import com.elead.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/jsp")
public class StatisticalController {
    @Autowired
    private BillService billService;

    @RequestMapping("/getTopnProduct")
    @ResponseBody
    public List<SmbmsStatistical> getTopnProduct(Date date) {

        java.sql.Date newdate=new java.sql.Date(date.getTime());
        return billService.getTopnProduct(newdate);
    }

    @RequestMapping("/getAllTopnProduct")
    @ResponseBody
    public List<SmbmsStatistical> getAllTopnProduct() {

        //java.sql.Date newdate=new java.sql.Date(date.getTime());
        return billService.getAllTopnProduct();
    }

    @RequestMapping("/getDayTotalPrice")
    @ResponseBody
    public List<SmbmsStatistical> getDayTotalPrice(Date date) {

        java.sql.Date newdate=new java.sql.Date(date.getTime());
        return billService.getDayTotalPrice(newdate);
    }

    @RequestMapping("/getProductPercent")
    @ResponseBody
    public List<SmbmsStatistical> getProductPercent() {

        //java.sql.Date newdate=new java.sql.Date(date.getTime());
        return billService.getProductPercent();
    }

    @RequestMapping("/getDayProductPercent")
    @ResponseBody
    public List<SmbmsStatistical> getDayProductPercent(Date date) {

        java.sql.Date newdate=new java.sql.Date(date.getTime());
        return billService.getDayProductPercent(newdate);
    }

    @RequestMapping("/getBillCount")
    @ResponseBody
    public List<SmbmsStatistical> getBillCount(Date date) {

        java.sql.Date newdate=new java.sql.Date(date.getTime());
        return billService.getBillCount(newdate);
    }


}

