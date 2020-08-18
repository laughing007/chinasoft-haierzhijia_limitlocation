package com.chinasoft.actualcombattable.actualcombattable.service.impl;

import com.chinasoft.actualcombattable.actualcombattable.dao.UserInfoDao;
import com.chinasoft.actualcombattable.actualcombattable.entity.UserInfo;
import com.chinasoft.actualcombattable.actualcombattable.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> getUserInfo(Integer userId) {
        return userInfoDao.getUserInfo(userId);
    }



}
