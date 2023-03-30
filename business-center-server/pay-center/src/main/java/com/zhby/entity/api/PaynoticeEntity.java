package com.zhby.entity.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaynoticeEntity {

    private String thirdpartguid;
    private String paystatus;
    private BigDecimal payamount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date paytime;
    private String paytypecode;

}
