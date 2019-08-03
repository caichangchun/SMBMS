package com.elead.service;

import com.elead.pojo.SmbmsRole;
import com.elead.pojo.SmbmsUser;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


public interface UserService {

     SmbmsUser loginInfo(SmbmsUser user);
     
     PageInfo<SmbmsUser> getAllUser(SmbmsUser user);

     PageInfo<SmbmsUser> selectUser(SmbmsUser user);

     SmbmsUser userdoService(SmbmsUser user);

     int userUpdate(SmbmsUser user);

     int deleteUser(Long userid);

     int addUser(SmbmsUser user);

     SmbmsUser selectUser_Smbms(String usercode);

     SmbmsUser selectUserById(Long id);

     List<SmbmsRole> getUserrole();

}
