package com.elead.service;

import com.elead.pojo.SmbmsProvider;
import com.github.pagehelper.PageInfo;

import java.awt.*;
import java.util.List;

public interface ProviderService {
    PageInfo<SmbmsProvider> getProvider(SmbmsProvider provider);

    List<SmbmsProvider> getAllProvider();

    int deleteByPrimaryKey(Long id);

    SmbmsProvider selectByPrimaryKey(Long id);

    Long selectCountByPrimaryKey(Long id);

    int updateByPrimaryKey(SmbmsProvider provider);

    int addProvider(SmbmsProvider provider);
}
