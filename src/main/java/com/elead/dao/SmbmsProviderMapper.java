package com.elead.dao;


import java.util.List;

import com.elead.pojo.SmbmsProvider;

public interface SmbmsProviderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmbmsProvider record);

    int insertSelective(SmbmsProvider record);

    SmbmsProvider selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmbmsProvider record);

    int updateByPrimaryKey(SmbmsProvider record);
    //得到所有供应商
    List<SmbmsProvider>getProviders(SmbmsProvider provider);

    List<SmbmsProvider>getAllProvider();
    //分页查询
    List<SmbmsProvider>searchProviders(SmbmsProvider provider);

    public Long selectCountByPrimaryKey(Long id);

}