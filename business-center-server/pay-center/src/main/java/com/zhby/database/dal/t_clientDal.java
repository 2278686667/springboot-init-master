package com.zhby.database.dal;


import com.zhby.base.BaseDal;
import com.zhby.database.dto.extTClientForSummary;
import com.zhby.database.model.pc_orderbill_tag;
import com.zhby.database.model.t_client;
import com.zhby.database.model.t_client_power;
import com.zhby.enums.CenterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public   class t_clientDal extends BaseDal<t_client>
{
    @Autowired
    private t_client_powerDal dTClientPower;

    public t_client getModelByPtCode(String ptcode) {
       return  this.getModelByPtCode(ptcode, null);
    }

    public t_client getModelByPtCode(String ptcode, CenterType centerType) {
        t_client client = jdbcDao.querySingleResultForSql("SELECT * FROM t_client WHERE NVL(Scbj,'0')='0' AND NVL(Shbj, '0')='1' AND clientcode=? ",
                new Object[]{ptcode}, t_client.class);
        if(null==client){
            throw new RuntimeException("平台" + ptcode + "非合法平台!");
        }
        if(null!=centerType&&!dTClientPower.isExistsPower(client.getRowguid(), centerType)) {
            throw new RuntimeException("平台无" + centerType + "权限!");
        }
        return client;
    }

    public t_client getModelByClientId(String clientId, CenterType centerType) {
        t_client client = this.getModel(clientId);
        if(null==client){
            throw new RuntimeException("平台" + clientId + "非合法平台!");
        }
        if(!dTClientPower.isExistsPower(client.getRowguid(), centerType)) {
            throw new RuntimeException("平台无" + centerType + "权限!");
        }
        return client;
    }


    public String getPowerUrl(String key, CenterType centerType) {
        return  this.getPowerUrl(key, centerType, "");
    }

    public String getPowerUrl(String key, CenterType centerType, String path) {
        t_client mTClient = this.getModelByPtCode(key, centerType);
        t_client_power mTClientPower = dTClientPower.getModelByPtCode(mTClient.getRowguid(), centerType);
        return (mTClient.getClienturl() + mTClientPower.getClinetpath()).replace("{path}", path);
    }



    public List<t_client> getClientSelect(CenterType centerType) {
        return jdbcDao.queryListForSqlNotNull("SELECT DISTINCT Rowguid, clientcode, Clientname from t_client A WHERE NVL(Scbj, '0')='0' AND NVL(Shbj, '0')='1' " +
                        " AND EXISTS (SELECT Rowguid FROM t_client_power " +
                        "              WHERE t_client_power.clientguid = A.Rowguid  AND Powercode=? AND NVL(Scbj,'0')='0' AND NVL(Shbj, '0')='1'  )" +
                        " ORDER BY ORDERNUM  ", new Object[]{centerType.toString()},
                t_client.class);
    }

    public List<extTClientForSummary> getListForSummary(String parentCondition, List<String> lstSqlParam) {
        return jdbcDao.queryListForSqlNotNull("SELECT DISTINCT Rowguid, clientcode, Clientname from t_client C " + parentCondition + " ORDER BY ORDERNUM",
                        lstSqlParam.toArray(), extTClientForSummary.class);
    }

}
