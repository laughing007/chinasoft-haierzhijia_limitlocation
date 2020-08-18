package com.chinasoft.actualcombattable.actualcombattable.dao;

import com.chinasoft.actualcombattable.actualcombattable.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class VisitRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<VisitRecordDao> getVisitRecord(Integer userId) {
        List<VisitRecordDao> list = jdbcTemplate.query("select * from visitrecord where userid =" + userId, new Object[]{}, new BeanPropertyRowMapper(UserInfo.class));
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public int insertVisitRecord(Integer userId, String ipAddress, Timestamp timestamp){
        int result = jdbcTemplate.update("insert into visitrecord (userid,ipaddress,timestamp )"+"values ('"+userId+"',  '" +ipAddress + "',  '" + timestamp +"')");
        return result;
    }

}
