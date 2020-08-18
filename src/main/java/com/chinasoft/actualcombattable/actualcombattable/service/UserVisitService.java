package com.chinasoft.actualcombattable.actualcombattable.service;

import com.chinasoft.actualcombattable.actualcombattable.dao.VisitRecordDao;

import java.sql.Timestamp;
import java.util.List;

public interface UserVisitService {
    public List<VisitRecordDao> getVisitRecord(Integer userId);
    public int insertVisitRecord(Integer userId, String ipAddress, Timestamp timestamp);
}
