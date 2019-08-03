package com.elead.service.Impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elead.dao.SmbmsRoleMapper;
import com.elead.pojo.SmbmsProvider;
import com.elead.pojo.SmbmsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elead.dao.SmbmsUserMapper;
import com.elead.pojo.SmbmsUser;
import com.elead.service.UserService;
import com.github.pagehelper.PageInfo;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SmbmsUserMapper smbmsUserMapper;
    @Autowired
    private SmbmsRoleMapper smbmsRoleMapper;

    @Override
    public SmbmsUser loginInfo(SmbmsUser user) {
        // TODO Auto-generated method stub
        return smbmsUserMapper.loginInfo(user);
    }

    @Override
    public PageInfo<SmbmsUser> getAllUser(SmbmsUser user) {
        // TODO Auto-generated method stub
        List<SmbmsUser> allUser = smbmsUserMapper.getAllUser(user);
//        for (SmbmsUser smbmsUser :allUser) {
//            System.out.println(smbmsUser.toString());
//        }
        PageInfo<SmbmsUser> pageInfo = new PageInfo<SmbmsUser>(allUser);
        return pageInfo;
    }

    @Override
    public SmbmsUser userdoService(SmbmsUser user) {
       int i= smbmsUserMapper.updateByPrimaryKey(user);
       if(i==1){
           user= smbmsUserMapper.selectByPrimaryuserCode_Smbms(user.getUsercode());
           return user;
       }else
           return null;
    }

    @Override
    public int userUpdate(SmbmsUser user){
        int i=smbmsUserMapper.updateByUserCode(user);
        if(i==1){
            return 1;
        }else
            return 0;
    }

    @Override
    public int deleteUser(Long userid) {
        int i= smbmsUserMapper.deleteByPrimaryKey(userid);
        if(i==1){
            return 1;
        }else
            //失败
            return 0;
    }

    @Override
    public int addUser(SmbmsUser user) {
        int i= smbmsUserMapper.insertuser(user);
        if(i==1){
            return 1;
        }else
            //失败
            return 0;
    }

    @Override
    public PageInfo<SmbmsUser> selectUser(SmbmsUser user) {
//        SmbmsUser user= smbmsUserMapper.selectByPrimaryuserCode(usercode);
//        return user;
        List<SmbmsUser> selectUser = smbmsUserMapper.selectByPrimaryuserCode(user);
        PageInfo<SmbmsUser> pageInfo = new PageInfo<SmbmsUser>(selectUser);
        return pageInfo;
    }

    @Override
    public SmbmsUser selectUser_Smbms(String usercode) {
        SmbmsUser user= smbmsUserMapper.selectByPrimaryuserCode_Smbms(usercode);
        return user;
    }

    @Override
    public SmbmsUser selectUserById(Long id) {
        SmbmsUser user= smbmsUserMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public List<SmbmsRole> getUserrole() {
        //Map<String, Object> map = new HashMap<String, Object>();
        List<SmbmsRole> getuserrole=smbmsRoleMapper.getUserrole();
        return getuserrole;
    }

}
