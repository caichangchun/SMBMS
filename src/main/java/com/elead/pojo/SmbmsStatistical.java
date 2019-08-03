package com.elead.pojo;

import java.util.Date;

public class SmbmsStatistical {
    /*
    每日产品销售排名
     */
    private Date date;
    private String productname;
    private double price;

    /*
    每日销售总额折线图
    */
    private Date daytotalprice_date;
    private double total;

    /*
    销售额组成饼状图
    */
    private String product_name;
    private double total_price;

    /*
    订单量折线图
     */
    private Date billcount_date;
    private double bill_count;

    /*
    每日饼图
     */
    private Date dayproductpercent_date;
    private String dayproductpercent_name;
    private double dayproductpercent_price;

    public Date getDayproductpercent_date() {
        return dayproductpercent_date;
    }

    public void setDayproductpercent_date(Date dayproductpercent_date) {
        this.dayproductpercent_date = dayproductpercent_date;
    }

    public String getDayproductpercent_name() {
        return dayproductpercent_name;
    }

    public void setDayproductpercent_name(String dayproductpercent_name) {
        this.dayproductpercent_name = dayproductpercent_name;
    }

    public double getDayproductpercent_price() {
        return dayproductpercent_price;
    }

    public void setDayproductpercent_price(double dayproductpercent_price) {
        this.dayproductpercent_price = dayproductpercent_price;
    }

    public Date getBillcount_date() {
        return billcount_date;
    }

    public void setBillcount_date(Date billcount_date) {
        this.billcount_date = billcount_date;
    }

    public double getBill_count() {
        return bill_count;
    }

    public void setBill_count(double bill_count) {
        this.bill_count = bill_count;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Date getDaytotalprice_date() {
        return daytotalprice_date;
    }

    public void setDaytotalprice_date(Date daytotalprice_date) {
        this.daytotalprice_date = daytotalprice_date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
