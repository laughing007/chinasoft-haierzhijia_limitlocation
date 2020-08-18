package com.chinasoft.actualcombattable.actualcombattable.entity;

import javax.persistence.*;
import java.sql.Timestamp;

public class UserInfo {
    private int id;
    private int userId;
    private String mlat1;
    private String mlat2;
    private String mlon1;
    private String mlon2;
    private String universityname;
    private Timestamp timeStamp;

    public String getUniversityname() {
        return universityname;
    }

    public void setUniversityname(String universityname) {
        this.universityname = universityname;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMlat1() {
        return mlat1;
    }

    public void setMlat1(String mlat1) {
        this.mlat1 = mlat1;
    }

    public String getMlat2() {
        return mlat2;
    }

    public void setMlat2(String mlat2) {
        this.mlat2 = mlat2;
    }

    public String getMlon1() {
        return mlon1;
    }

    public void setMlon1(String mlon1) {
        this.mlon1 = mlon1;
    }

    public String getMlon2() {
        return mlon2;
    }

    public void setMlon2(String mlon2) {
        this.mlon2 = mlon2;
    }
}
