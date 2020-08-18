package com.chinasoft.actualcombattable.actualcombattable.controller;

import com.alibaba.druid.util.StringUtils;
import com.chinasoft.actualcombattable.actualcombattable.entity.UserInfo;
import com.chinasoft.actualcombattable.actualcombattable.service.UserInfoService;
import com.chinasoft.actualcombattable.actualcombattable.service.UserVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

@RestController
public class mytest {

//    @Autowired
//    UserQuery userQuery;

    private static Logger logger = LoggerFactory.getLogger(mytest.class);
    private static final String HTTP = "http://api.map.baidu.com/location/ip";
    private static final String HTTPS = "https://api.map.baidu.com/location/ip";
    private static final String AK = "7u56CLE3LXddD8UTc803LNedwAnwvT8I";
    private static final String COOR = "bd09ll";

    /*@Resource
    private RestTemplate restTemplate;*/
    private RestTemplate restTemplate = new RestTemplate();

    public JSONObject getDate(String ip) {
        String url = HTTPS+"?coor=bd09ll&ak="+AK+"&ip="+ip;
        return restTemplate.getForObject(url, JSONObject.class);
    }

    @GetMapping(path = "/index/{userId}/{ipAddress}")  // {ipAddress} @PathVariable(name = "ipAddress") String ipAddress,
    String index(@PathVariable(name = "userId") Integer userId,@PathVariable(name = "ipAddress") String ipAddress){
//        @PathVariable(name = "lat1") long lat1,
//        @PathVariable(name = "lon1") long lon1
        String ip = "39.88.151.151"; // 访问IP
        Timestamp curT = new Timestamp(System.currentTimeMillis());
        int insertResult = userVisitService.insertVisitRecord(userId,ipAddress,curT);
        logger.info("插入结果是: ==>>>", "insertResult==>>"+insertResult, "insertResult==>>>"+insertResult);
        JSONObject jsonObject = getDate(ipAddress);
        logger.info("result={}", jsonObject.toJSONString());
        String mLatitude ="", mLongitude = "";
        if (jsonObject != null) {
            JSONObject json = jsonObject.getJSONObject("content");
            logger.info("地址={}", json.get("address"));
            JSONObject pointJSON = json.getJSONObject("point");
            mLatitude = (String)pointJSON.get("x");
            mLongitude = (String)pointJSON.get("y");
            logger.info("经度={}; 纬度={}", mLatitude, mLongitude);
        }
//        List<UserInfo> mUserList = userQuery.findByUserId(1L);
        if(StringUtils.isEmpty(mLatitude) || StringUtils.isEmpty(mLongitude)){
            return "false";
        }
        String tmpResult = "false";
        Long mmlat1 = Long.valueOf(mLatitude.replace(".",""));
        Long mmlon1 = Long.valueOf(mLongitude.replace(".",""));
        List<UserInfo> mList = personService.getUserInfo(userId);
        if(mList != null && mList.size() > 0){
            Iterator mIterator = mList.iterator();
            while (mIterator.hasNext()){
                Long mLat1,mLat2,mLon1,mLon2;
                int mUserId = 0;
                UserInfo mUserInfo = (UserInfo)mIterator.next();
                mUserId = mUserInfo.getUserId();
                mLat1 = Long.valueOf(mUserInfo.getMlat1().replace(".",""));
                mLat2 = Long.valueOf(mUserInfo.getMlat2().replace(".",""));
                mLon1 = Long.valueOf(mUserInfo.getMlon1().replace(".",""));
                mLon2 = Long.valueOf(mUserInfo.getMlon2().replace(".",""));
                logger.info("经度={}; 纬度={}", mLat1, mLat2);
                logger.info("经度={}; 纬度={}", mLon1, mLon2);
                if(mUserId == userId && mmlat1 > mLat1 && mmlat1 < mLat2 && mmlon1 > mLon1 && mmlon1 < mLon2){
                    tmpResult = "true";
                }
            }
        }
        return tmpResult; //"latitude = "+mLatitude + "<==longitude=>>" + mLongitude;
    }

    @Autowired
    UserVisitService userVisitService;

    @Autowired
    UserInfoService personService;

    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    public List<UserInfo> getUserInfo(@PathVariable(name = "userId") Integer userId) {
        return personService.getUserInfo(userId);
    }

}
