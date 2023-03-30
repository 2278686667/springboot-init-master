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
public class uc_zhuti_accountversion_detail implements Serializable
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    public static final String[] fields = new String[]{"Rowguid","Accountversionguid","Accountguid","Tablename","Filedname","Prevcontent","Ordernum","Addtime","Adduserid","Addusername","Adduserdeptid","Adduserdeptname","Adddwcode","Adddwname","Detail","Shbj","Scbj","Createdate","Modifydate","Mdiuserid","Mdiusername","Mdidwcode","Mdidwname"};

    @Column(name = "ROWGUID")
    @Length(max=100,message="Rowguid超过限制长度，请修改")
    private   String rowguid; 
    
    @Column(name = "ACCOUNTVERSIONGUID")
    @Length(max=100,message="Accountversionguid超过限制长度，请修改")
    private   String accountversionguid; 
    
    @Column(name = "ACCOUNTGUID")
    @Length(max=100,message="Accountguid超过限制长度，请修改")
    private   String accountguid; 
    
    @Column(name = "TABLENAME")
    @Length(max=100,message="表名超过限制长度，请修改")
    private   String tablename; 
    
    @Column(name = "FILEDNAME")
    @Length(max=4000,message="修改字段超过限制长度，请修改")
    private   String filedname; 
    
    @Column(name = "PREVCONTENT")
    @Length(max=4000,message="上个版本内容超过限制长度，请修改")
    private   String prevcontent; 
    
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
	* Accountversionguid
    */  	
    public  String getAccountversionguid()
    {
        return  accountversionguid;             
    }     
    /**
	* Accountversionguid
    */  	
    public void setAccountversionguid (String ACCOUNTVERSIONGUID )
    {
        accountversionguid=ACCOUNTVERSIONGUID;
    }     

    /**
	* Accountguid
    */  	
    public  String getAccountguid()
    {
        return  accountguid;             
    }     
    /**
	* Accountguid
    */  	
    public void setAccountguid (String ACCOUNTGUID )
    {
        accountguid=ACCOUNTGUID;
    }     

    /**
	* 表名
    */  	
    public  String getTablename()
    {
        return  tablename;             
    }     
    /**
	* 表名
    */  	
    public void setTablename (String TABLENAME )
    {
        tablename=TABLENAME;
    }     

    /**
	* 修改字段
    */  	
    public  String getFiledname()
    {
        return  filedname;             
    }     
    /**
	* 修改字段
    */  	
    public void setFiledname (String FILEDNAME )
    {
        filedname=FILEDNAME;
    }     

    /**
	* 上个版本内容
    */  	
    public  String getPrevcontent()
    {
        return  prevcontent;             
    }     
    /**
	* 上个版本内容
    */  	
    public void setPrevcontent (String PREVCONTENT )
    {
        prevcontent=PREVCONTENT;
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
        


    public <T extends uc_zhuti_accountversion_detail>  T modelToDto(Class<T> tClass) {
        T dto = null;
        try {
    		dto=tClass.newInstance();
		} catch (Exception e) {
			System.out.println("Impossible");
		}
	    dto.setRowguid(this.getRowguid());
	    dto.setAccountversionguid(this.getAccountversionguid());
	    dto.setAccountguid(this.getAccountguid());
	    dto.setTablename(this.getTablename());
	    dto.setFiledname(this.getFiledname());
	    dto.setPrevcontent(this.getPrevcontent());
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
        return dto;
    } 

}
 