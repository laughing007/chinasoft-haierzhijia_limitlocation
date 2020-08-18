package com.chinasoft.actualcombattable.actualcombattable.dao;

import com.chinasoft.actualcombattable.actualcombattable.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserInfo> getUserInfo(Integer userId) {

        List<UserInfo> list = jdbcTemplate.query("select * from userinfo where userid =" + userId, new Object[]{}, new BeanPropertyRowMapper(UserInfo.class));
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

}
