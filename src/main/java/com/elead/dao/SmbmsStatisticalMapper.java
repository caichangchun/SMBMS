package com.elead.dao;

import com.elead.pojo.SmbmsStatistical;

import java.util.Date;
import java.util.List;

public interface SmbmsStatisticalMapper {
    List<SmbmsStatistical> getTopnProduct(Date date);

    List<SmbmsStatistical> getAllTopnProduct();

    List<SmbmsStatistical> getDayTotalPrice(Date date);

    List<SmbmsStatistical> getProductPercent();

    List<SmbmsStatistical> getDayProductPercent(Date date);

    List<SmbmsStatistical> getBillCount(Date date);
}
