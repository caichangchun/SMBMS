package com.elead.dao;


import java.util.List;

import com.elead.pojo.SmbmsBill;

public interface SmbmsBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmbmsBill record);

    int insertList(List<SmbmsBill> list);

    int insertSelective(SmbmsBill record);

    SmbmsBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmbmsBill record);

    int updateByPrimaryKey(SmbmsBill record);
    
    //遍历所有商品
    List<SmbmsBill> getAllBill(SmbmsBill bill);//分页

}