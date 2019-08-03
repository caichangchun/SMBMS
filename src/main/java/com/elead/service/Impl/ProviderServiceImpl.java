package com.elead.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elead.dao.SmbmsProviderMapper;
import com.elead.pojo.SmbmsProvider;
import com.elead.service.ProviderService;
import com.github.pagehelper.PageInfo;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private SmbmsProviderMapper smbmsProviderMapper;

    @Override
    public PageInfo<SmbmsProvider> getProvider(SmbmsProvider provider) {
        // TODO Auto-generated method stub
//        if(provider.getProcode()==null&&provider.getProname()==null){
        List<SmbmsProvider> providers = smbmsProviderMapper.getProviders(provider);
        PageInfo<SmbmsProvider> pageInfo = new PageInfo<SmbmsProvider>(providers);
        return pageInfo;
//        }
//        else{
//            List<SmbmsProvider> providers = smbmsProviderMapper.searchProviders(provider);
//            PageInfo<SmbmsProvider> pageInfo = new PageInfo<SmbmsProvider>(providers);
//            return pageInfo;
//        }

    }

    @Override
    public int deleteByPrimaryKey(Long id){
        return smbmsProviderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmbmsProvider selectByPrimaryKey(Long id){
        return smbmsProviderMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long selectCountByPrimaryKey(Long id){
        return smbmsProviderMapper.selectCountByPrimaryKey(id);
    }

    @Override
    public  int updateByPrimaryKey(SmbmsProvider provider){
        return smbmsProviderMapper.updateByPrimaryKey(provider);
    }

    @Override
    public int addProvider(SmbmsProvider provider){
        return smbmsProviderMapper.insert(provider);
    }

    @Override
    public List<SmbmsProvider> getAllProvider(){
        return smbmsProviderMapper.getAllProvider();
    }
}
