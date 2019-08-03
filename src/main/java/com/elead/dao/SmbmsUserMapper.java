package com.elead.dao;


import java.util.List;
import java.util.Map;

import com.elead.pojo.SmbmsRole;
import com.elead.pojo.SmbmsUser;
import org.apache.ibatis.annotations.Param;

public interface SmbmsUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmbmsUser record);

    int insertuser(SmbmsUser record);

    int insertSelective(SmbmsUser record);

    SmbmsUser selectByPrimaryKey(Long id);

    List<SmbmsUser>selectByPrimaryuserCode(SmbmsUser user);

    SmbmsUser selectByPrimaryuserCode_Smbms(String usercode);

    int updateByPrimaryKeySelective(SmbmsUser record);

    int updateByUserCode(SmbmsUser user);

    int updateByPrimaryKey(SmbmsUser record);
    
    SmbmsUser loginInfo(SmbmsUser user);
    
    List<SmbmsUser>getAllUser(SmbmsUser user);//分页

   
}