package com.zhby.database.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

/**
 *
 */
public class t_client implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String[] fields = new String[]{"Rowguid","Clientcode","Clientname","Clienturl","Clientpublickey","Ordernum","Addtime","Adduserid","Addusername","Adduserdeptid","Adduserdeptname","Adddwcode","Adddwname","Detail","Shbj","Scbj","Createdate","Modifydate","Mdiuserid","Mdiusername","Mdidwcode","Mdidwname","Oauthclientsecret","Oauthallowurl","Oauthscope","Oauthmode"};

    @Id
    @Column(name = "ROWGUID")
    @Length(max=100,message="Rowguid超过限制长度，请修改")
    private   String rowguid;

    @Column(name = "CLIENTCODE")
    @Length(max=100,message="Clientcode超过限制长度，请修改")
    private   String clientcode;

    @Column(name = "CLIENTNAME")
    @Length(max=100,message="Clientname超过限制长度，请修改")
    private   String clientname;

    @Column(name = "CLIENTURL")
    @Length(max=400,message="Clienturl超过限制长度，请修改")
    private   String clienturl;

    @Column(name = "CLIENTPUBLICKEY")
    @Length(max=400,message="Clientpublickey超过限制长度，请修改")
    private   String clientpublickey;

    @Column(name = "ORDERNUM")
    private   BigDecimal ordernum;

    @Column(name = "ADDTIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private   Date addtime;

    @Column(name = "ADDUSERID")
    private   BigDecimal adduserid;

    @Column(name = "ADDUSERNAME")
    @Length(max=50,message="添加人超过限制长度，请修改")
    private   String addusername;

    @Column(name = "ADDUSERDEPTID")
    @Length(max=50,message="添加人部门id超过限制长度，请修改")
    private   String adduserdeptid;

    @Column(name = "ADDUSERDEPTNAME")
    @Length(max=50,message="添加人部门名称超过限制长度，请修改")
    private   String adduserdeptname;

    @Column(name = "ADDDWCODE")
    @Length(max=50,message="添加单位编码超过限制长度，请修改")
    private   String adddwcode;

    @Column(name = "ADDDWNAME")
    @Length(max=100,message="添加单位名称超过限制长度，请修改")
    private   String adddwname;

    @Column(name = "DETAIL")
    @Length(max=2000,message="备注超过限制长度，请修改")
    private   String detail;

    @Column(name = "SHBJ")
    @Length(max=1,message="审核标记超过限制长度，请修改")
    private   String shbj;

    @Column(name = "SCBJ")
    @Length(max=1,message="删除标记超过限制长度，请修改")
    private   String scbj;

    @Column(name = "CREATEDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private   Date createdate;

    @Column(name = "MODIFYDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private   Date modifydate;

    @Column(name = "MDIUSERID")
    private   BigDecimal mdiuserid;

    @Column(name = "MDIUSERNAME")
    @Length(max=50,message="修改人姓名超过限制长度，请修改")
    private   String mdiusername;

    @Column(name = "MDIDWCODE")
    @Length(max=50,message="修改单位编码超过限制长度，请修改")
    private   String mdidwcode;

    @Column(name = "MDIDWNAME")
    @Length(max=100,message="修改单位名称超过限制长度，请修改")
    private   String mdidwname;

    @Column(name = "OAUTHCLIENTSECRET")
    @Length(max=100,message="OAUTH授权秘钥超过限制长度，请修改")
    private   String oauthclientsecret;

    @Column(name = "OAUTHALLOWURL")
    @Length(max=4000,message="OAUTH授权地址超过限制长度，请修改")
    private   String oauthallowurl;

    @Column(name = "OAUTHSCOPE")
    @Length(max=100,message="OAUTH权限超过限制长度，请修改")
    private   String oauthscope;

    @Column(name = "OAUTHMODE")
    @Length(max=100,message="OAUTH授权方式超过限制长度，请修改")
    private   String oauthmode;


    /**
     * Rowguid
     */
    public  String getRowguid()
    {
        return  rowguid;
    }
    /**
     * Rowguid
     */
    public void setRowguid (String ROWGUID )
    {
        rowguid=ROWGUID;
    }

    /**
     * Clientcode
     */
    public  String getClientcode()
    {
        return  clientcode;
    }
    /**
     * Clientcode
     */
    public void setClientcode (String CLIENTCODE )
    {
        clientcode=CLIENTCODE;
    }

    /**
     * Clientname
     */
    public  String getClientname()
    {
        return  clientname;
    }
    /**
     * Clientname
     */
    public void setClientname (String CLIENTNAME )
    {
        clientname=CLIENTNAME;
    }

    /**
     * Clienturl
     */
    public  String getClienturl()
    {
        return  clienturl;
    }
    /**
     * Clienturl
     */
    public void setClienturl (String CLIENTURL )
    {
        clienturl=CLIENTURL;
    }

    /**
     * Clientpublickey
     */
    public  String getClientpublickey()
    {
        return  clientpublickey;
    }
    /**
     * Clientpublickey
     */
    public void setClientpublickey (String CLIENTPUBLICKEY )
    {
        clientpublickey=CLIENTPUBLICKEY;
    }

    /**
     * 排序
     */
    public  BigDecimal getOrdernum()
    {
        return  ordernum;
    }
    /**
     * 排序
     */
    public void setOrdernum (BigDecimal ORDERNUM )
    {
        ordernum=ORDERNUM;
    }

    /**
     * 添加时间
     */
    public  Date getAddtime()
    {
        return  addtime;
    }
    /**
     * 添加时间
     */
    public void setAddtime (Date ADDTIME )
    {
        addtime=ADDTIME;
    }

    /**
     * 添加人id
     */
    public  BigDecimal getAdduserid()
    {
        return  adduserid;
    }
    /**
     * 添加人id
     */
    public void setAdduserid (BigDecimal ADDUSERID )
    {
        adduserid=ADDUSERID;
    }

    /**
     * 添加人
     */
    public  String getAddusername()
    {
        return  addusername;
    }
    /**
     * 添加人
     */
    public void setAddusername (String ADDUSERNAME )
    {
        addusername=ADDUSERNAME;
    }

    /**
     * 添加人部门id
     */
    public  String getAdduserdeptid()
    {
        return  adduserdeptid;
    }
    /**
     * 添加人部门id
     */
    public void setAdduserdeptid (String ADDUSERDEPTID )
    {
        adduserdeptid=ADDUSERDEPTID;
    }

    /**
     * 添加人部门名称
     */
    public  String getAdduserdeptname()
    {
        return  adduserdeptname;
    }
    /**
     * 添加人部门名称
     */
    public void setAdduserdeptname (String ADDUSERDEPTNAME )
    {
        adduserdeptname=ADDUSERDEPTNAME;
    }

    /**
     * 添加单位编码
     */
    public  String getAdddwcode()
    {
        return  adddwcode;
    }
    /**
     * 添加单位编码
     */
    public void setAdddwcode (String ADDDWCODE )
    {
        adddwcode=ADDDWCODE;
    }

    /**
     * 添加单位名称
     */
    public  String getAdddwname()
    {
        return  adddwname;
    }
    /**
     * 添加单位名称
     */
    public void setAdddwname (String ADDDWNAME )
    {
        adddwname=ADDDWNAME;
    }

    /**
     * 备注
     */
    public  String getDetail()
    {
        return  detail;
    }
    /**
     * 备注
     */
    public void setDetail (String DETAIL )
    {
        detail=DETAIL;
    }

    /**
     * 审核标记
     */
    public  String getShbj()
    {
        return  shbj;
    }
    /**
     * 审核标记
     */
    public void setShbj (String SHBJ )
    {
        shbj=SHBJ;
    }

    /**
     * 删除标记
     */
    public  String getScbj()
    {
        return  scbj;
    }
    /**
     * 删除标记
     */
    public void setScbj (String SCBJ )
    {
        scbj=SCBJ;
    }

    /**
     * 建立时间
     */
    public  Date getCreatedate()
    {
        return  createdate;
    }
    /**
     * 建立时间
     */
    public void setCreatedate (Date CREATEDATE )
    {
        createdate=CREATEDATE;
    }

    /**
     * 修改时间
     */
    public  Date getModifydate()
    {
        return  modifydate;
    }
    /**
     * 修改时间
     */
    public void setModifydate (Date MODIFYDATE )
    {
        modifydate=MODIFYDATE;
    }

    /**
     * 修改用户id
     */
    public  BigDecimal getMdiuserid()
    {
        return  mdiuserid;
    }
    /**
     * 修改用户id
     */
    public void setMdiuserid (BigDecimal MDIUSERID )
    {
        mdiuserid=MDIUSERID;
    }

    /**
     * 修改人姓名
     */
    public  String getMdiusername()
    {
        return  mdiusername;
    }
    /**
     * 修改人姓名
     */
    public void setMdiusername (String MDIUSERNAME )
    {
        mdiusername=MDIUSERNAME;
    }

    /**
     * 修改单位编码
     */
    public  String getMdidwcode()
    {
        return  mdidwcode;
    }
    /**
     * 修改单位编码
     */
    public void setMdidwcode (String MDIDWCODE )
    {
        mdidwcode=MDIDWCODE;
    }

    /**
     * 修改单位名称
     */
    public  String getMdidwname()
    {
        return  mdidwname;
    }
    /**
     * 修改单位名称
     */
    public void setMdidwname (String MDIDWNAME )
    {
        mdidwname=MDIDWNAME;
    }

    /**
     * OAUTH授权秘钥
     */
    public  String getOauthclientsecret()
    {
        return  oauthclientsecret;
    }
    /**
     * OAUTH授权秘钥
     */
    public void setOauthclientsecret (String OAUTHCLIENTSECRET )
    {
        oauthclientsecret=OAUTHCLIENTSECRET;
    }

    /**
     * OAUTH授权地址
     */
    public  String getOauthallowurl()
    {
        return  oauthallowurl;
    }
    /**
     * OAUTH授权地址
     */
    public void setOauthallowurl (String OAUTHALLOWURL )
    {
        oauthallowurl=OAUTHALLOWURL;
    }

    /**
     * OAUTH权限
     */
    public  String getOauthscope()
    {
        return  oauthscope;
    }
    /**
     * OAUTH权限
     */
    public void setOauthscope (String OAUTHSCOPE )
    {
        oauthscope=OAUTHSCOPE;
    }

    /**
     * OAUTH授权方式
     */
    public  String getOauthmode()
    {
        return  oauthmode;
    }
    /**
     * OAUTH授权方式
     */
    public void setOauthmode (String OAUTHMODE )
    {
        oauthmode=OAUTHMODE;
    }



    public <T extends t_client>  T modelToDto(Class<T> tClass) {
        T dto = null;
        try {
            dto=tClass.newInstance();
        } catch (Exception e) {
            System.out.println("Impossible");
        }
        dto.setRowguid(this.getRowguid());
        dto.setClientcode(this.getClientcode());
        dto.setClientname(this.getClientname());
        dto.setClienturl(this.getClienturl());
        dto.setClientpublickey(this.getClientpublickey());
        dto.setOrdernum(this.getOrdernum());
        dto.setAddtime(this.getAddtime());
        dto.setAdduserid(this.getAdduserid());
        dto.setAddusername(this.getAddusername());
        dto.setAdduserdeptid(this.getAdduserdeptid());
        dto.setAdduserdeptname(this.getAdduserdeptname());
        dto.setAdddwcode(this.getAdddwcode());
        dto.setAdddwname(this.getAdddwname());
        dto.setDetail(this.getDetail());
        dto.setShbj(this.getShbj());
        dto.setScbj(this.getScbj());
        dto.setCreatedate(this.getCreatedate());
        dto.setModifydate(this.getModifydate());
        dto.setMdiuserid(this.getMdiuserid());
        dto.setMdiusername(this.getMdiusername());
        dto.setMdidwcode(this.getMdidwcode());
        dto.setMdidwname(this.getMdidwname());
        dto.setOauthclientsecret(this.getOauthclientsecret());
        dto.setOauthallowurl(this.getOauthallowurl());
        dto.setOauthscope(this.getOauthscope());
        dto.setOauthmode(this.getOauthmode());
        return dto;
    }

}
 