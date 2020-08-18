package com.chinasoft.actualcombattable.actualcombattable.service.impl;

import com.chinasoft.actualcombattable.actualcombattable.dao.VisitRecordDao;
import com.chinasoft.actualcombattable.actualcombattable.service.UserVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserVisitServiceImpl implements UserVisitService {

    @Autowired
    VisitRecordDao visitRecordDao;

    @Override
    public List<VisitRecordDao> getVisitRecord(Integer userId) {
        return visitRecordDao.getVisitRecord(userId);
    }

    @Override
    public int insertVisitRecord(Integer userId, String ipAddress, Timestamp timestamp) {
        return visitRecordDao.insertVisitRecord(userId,ipAddress,timestamp);
    }

}
