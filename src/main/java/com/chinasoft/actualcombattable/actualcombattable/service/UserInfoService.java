package com.chinasoft.actualcombattable.actualcombattable.service;

import com.chinasoft.actualcombattable.actualcombattable.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    public List<UserInfo> getUserInfo(Integer userId);
}
