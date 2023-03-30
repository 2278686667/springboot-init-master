package com.zhby.service;

import com.zhby.database.dal.t_clientDal;
import com.zhby.database.model.t_client;
import com.zhby.enums.CenterType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PushService extends AbstractPushService {

    @Autowired
    private t_clientDal dTClient;

    protected String getThirdPublickey(String ZH_YW_CODE){
        t_client mTClient = dTClient.getModelByPtCode(ZH_YW_CODE);
        return mTClient.getClientpublickey();
    }

}
