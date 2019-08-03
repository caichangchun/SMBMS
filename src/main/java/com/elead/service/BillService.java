package com.elead.service;

import com.elead.pojo.SmbmsBill;
import com.elead.pojo.SmbmsProvider;
import com.elead.pojo.SmbmsStatistical;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.Date;
import java.util.List;

public interface BillService {
    PageInfo<SmbmsBill>getAllBill(SmbmsBill bill);

    SmbmsBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmbmsBill bill);

    int deleteByPrimaryKey(Long id);

    int insert(SmbmsBill bill);

    int insertList(List<SmbmsBill> list);

    List<SmbmsStatistical> getTopnProduct(Date date);

    List<SmbmsStatistical> getAllTopnProduct();

    List<SmbmsStatistical> getDayTotalPrice(Date date);

    List<SmbmsStatistical> getProductPercent();

    List<SmbmsStatistical> getDayProductPercent(Date date);

    List<SmbmsStatistical> getBillCount(Date date);

    List<SmbmsBill> resolveExcel(MultipartFile file);

}
