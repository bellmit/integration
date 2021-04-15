package com.qunjie.mysql.service;

import com.qunjie.mysql.mapper.UserValueMapper;
import com.qunjie.mysql.model.UserValue;
import com.qunjie.mysql.param.UserValueParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.mysql.service.UserValueService
 *
 * @author whs
 * Date:   2020/12/21  17:21
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class UserValueService {

    @Autowired
    private UserValueMapper userValueMapper;

    public int add(UserValue userValue){
        if (userValue == null || userValue.getUserid() == null || userValue.getOpenuserid() == null){
            return 1;
        }
        return userValueMapper.insertUser(userValue);
    }

    public int update(UserValue value){
        if (value == null || value.getUserid() == null || value.getOpenuserid() == null){
            return 1;
        }
        return userValueMapper.updUser(value);
    }

    public UserValue findByCondition(UserValueParam valueParam){
        return userValueMapper.findByCondition(valueParam);
    }

    public UserValue findByCondition(Integer userid){
        UserValueParam valueParam = new UserValueParam();
        valueParam.setUserid(userid);
        return userValueMapper.findByCondition(valueParam);
    }

    public int delete(Integer userId){
        UserValueParam userValueParam = new UserValueParam();
        userValueParam.setUserid(userId);
        return userValueMapper.delete(userValueParam);
    }

    public List<UserValue> findByCondition2(UserValue userValue){
        return userValueMapper.findByCondition2(userValue);
    }

    public String getCrmOpenUserIdByNm(String fanweinm){
        String res = "";
        UserValue userValue = new UserValue();
        userValue.setUsernm(fanweinm);
        List<UserValue> userValues = findByCondition2(userValue);
        if (!CollectionUtils.isEmpty(userValues)){
            res = userValues.get(0).getOpenuserid();
        }
        return res;
    }
}
