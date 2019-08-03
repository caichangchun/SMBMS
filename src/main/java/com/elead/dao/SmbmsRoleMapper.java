package com.elead.dao;


import com.elead.pojo.SmbmsRole;

import java.util.List;

public interface SmbmsRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmbmsRole record);

    int insertSelective(SmbmsRole record);

    SmbmsRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmbmsRole record);

    int updateByPrimaryKey(SmbmsRole record);

    List<SmbmsRole> getUserrole();//获取用户角色
}