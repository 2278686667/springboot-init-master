package com.zhby.database.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

/**
*功能菜单
*/ 
public class t_sysmenu implements Serializable
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    public static final String[] fields = new String[]{"Menuid","Parentid","Menutype","Imgpath","Menuname","Title","Pageurl","Sfmx","Shbj","Scbj","Modifydate","Createdate","Targetblank","Linkmenuid","Detail3","Detail2","Detail","Menuversion","Projecttype","Content","Ext3childtype","Newpageurl"};

    @Id  
    @Column(name = "MENUID")
    @Length(max=20,message="菜单id超过限制长度，请修改")
    private   String menuid; 
    
    @Column(name = "PARENTID")
    @Length(max=20,message="父id超过限制长度，请修改")
    private   String parentid; 
    
    @Column(name = "MENUTYPE")
    @Length(max=2,message="菜单类型超过限制长度，请修改")
    private   String menutype; 
    
    @Column(name = "IMGPATH")
    @Length(max=50,message="图片路径超过限制长度，请修改")
    private   String imgpath; 
    
    @Column(name = "MENUNAME")
    @Length(max=50,message="菜单名称超过限制长度，请修改")
    private   String menuname; 
    
    @Column(name = "TITLE")
    @Length(max=50,message="标题提示超过限制长度，请修改")
    private   String title; 
    
    @Column(name = "PAGEURL")
    @Length(max=100,message="页面url超过限制长度，请修改")
    private   String pageurl; 
    
    @Column(name = "SFMX")
    @Length(max=0,message="是否明细超过限制长度，请修改")
    private   String sfmx; 
    
    @Column(name = "SHBJ")
    @Length(max=0,message="审核标记超过限制长度，请修改")
    private   String shbj; 
    
    @Column(name = "SCBJ")
    @Length(max=0,message="删除标记超过限制长度，请修改")
    private   String scbj; 
    
    @Column(name = "MODIFYDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private   Date modifydate; 
    
    @Column(name = "CREATEDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private   Date createdate; 
    
    @Column(name = "TARGETBLANK")
    @Length(max=1,message="Targetblank超过限制长度，请修改")
    private   String targetblank; 
    
    @Column(name = "LINKMENUID")
    @Length(max=20,message="关联菜单代码，用于新旧投标系统菜单关联超过限制长度，请修改")
    private   String linkmenuid; 
    
    @Column(name = "DETAIL3")
    @Length(max=4000,message="Detail3超过限制长度，请修改")
    private   String detail3; 
    
    @Column(name = "DETAIL2")
    @Length(max=4000,message="Detail2超过限制长度，请修改")
    private   String detail2; 
    
    @Column(name = "DETAIL")
    @Length(max=1000,message="Detail超过限制长度，请修改")
    private   String detail; 
    
    @Column(name = "MENUVERSION")
    @Length(max=10,message="菜单版本超过限制长度，请修改")
    private   String menuversion; 
    
    @Column(name = "PROJECTTYPE")
    @Length(max=0,message="政采项目类型标记超过限制长度，请修改")
    private   String projecttype; 
    
    @Column(name = "CONTENT")
    @Length(max=4000,message="菜单介绍内容超过限制长度，请修改")
    private   String content; 
    
    @Column(name = "EXT3CHILDTYPE")
    @Length(max=0,message="其它类型超过限制长度，请修改")
    private   String ext3childtype; 
    
    @Column(name = "NEWPAGEURL")
    @Length(max=100,message="新菜单地址，由于历史数据投标和代理公用了一部分菜单，但地址不能复用，投标系统使用此字段作为地址超过限制长度，请修改")
    private   String newpageurl; 
    

    /**
	* 菜单id
    */  	
    public  String getMenuid()
    {
        return  menuid;             
    }     
    /**
	* 菜单id
    */  	
    public void setMenuid (String MENUID )
    {
        menuid=MENUID;
    }     

    /**
	* 父id
    */  	
    public  String getParentid()
    {
        return  parentid;             
    }     
    /**
	* 父id
    */  	
    public void setParentid (String PARENTID )
    {
        parentid=PARENTID;
    }     

    /**
	* 菜单类型
    */  	
    public  String getMenutype()
    {
        return  menutype;             
    }     
    /**
	* 菜单类型
    */  	
    public void setMenutype (String MENUTYPE )
    {
        menutype=MENUTYPE;
    }     

    /**
	* 图片路径
    */  	
    public  String getImgpath()
    {
        return  imgpath;             
    }     
    /**
	* 图片路径
    */  	
    public void setImgpath (String IMGPATH )
    {
        imgpath=IMGPATH;
    }     

    /**
	* 菜单名称
    */  	
    public  String getMenuname()
    {
        return  menuname;             
    }     
    /**
	* 菜单名称
    */  	
    public void setMenuname (String MENUNAME )
    {
        menuname=MENUNAME;
    }     

    /**
	* 标题提示
    */  	
    public  String getTitle()
    {
        return  title;             
    }     
    /**
	* 标题提示
    */  	
    public void setTitle (String TITLE )
    {
        title=TITLE;
    }     

    /**
	* 页面url
    */  	
    public  String getPageurl()
    {
        return  pageurl;             
    }     
    /**
	* 页面url
    */  	
    public void setPageurl (String PAGEURL )
    {
        pageurl=PAGEURL;
    }     

    /**
	* 是否明细
    */  	
    public  String getSfmx()
    {
        return  sfmx;             
    }     
    /**
	* 是否明细
    */  	
    public void setSfmx (String SFMX )
    {
        sfmx=SFMX;
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
	* 创建时间
    */  	
    public  Date getCreatedate()
    {
        return  createdate;             
    }     
    /**
	* 创建时间
    */  	
    public void setCreatedate (Date CREATEDATE )
    {
        createdate=CREATEDATE;
    }     

    /**
	* Targetblank
    */  	
    public  String getTargetblank()
    {
        return  targetblank;             
    }     
    /**
	* Targetblank
    */  	
    public void setTargetblank (String TARGETBLANK )
    {
        targetblank=TARGETBLANK;
    }     

    /**
	* 关联菜单代码，用于新旧投标系统菜单关联
    */  	
    public  String getLinkmenuid()
    {
        return  linkmenuid;             
    }     
    /**
	* 关联菜单代码，用于新旧投标系统菜单关联
    */  	
    public void setLinkmenuid (String LINKMENUID )
    {
        linkmenuid=LINKMENUID;
    }     

    /**
	* Detail3
    */  	
    public  String getDetail3()
    {
        return  detail3;             
    }     
    /**
	* Detail3
    */  	
    public void setDetail3 (String DETAIL3 )
    {
        detail3=DETAIL3;
    }     

    /**
	* Detail2
    */  	
    public  String getDetail2()
    {
        return  detail2;             
    }     
    /**
	* Detail2
    */  	
    public void setDetail2 (String DETAIL2 )
    {
        detail2=DETAIL2;
    }     

    /**
	* Detail
    */  	
    public  String getDetail()
    {
        return  detail;             
    }     
    /**
	* Detail
    */  	
    public void setDetail (String DETAIL )
    {
        detail=DETAIL;
    }     

    /**
	* 菜单版本
    */  	
    public  String getMenuversion()
    {
        return  menuversion;             
    }     
    /**
	* 菜单版本
    */  	
    public void setMenuversion (String MENUVERSION )
    {
        menuversion=MENUVERSION;
    }     

    /**
	* 政采项目类型标记
    */  	
    public  String getProjecttype()
    {
        return  projecttype;             
    }     
    /**
	* 政采项目类型标记
    */  	
    public void setProjecttype (String PROJECTTYPE )
    {
        projecttype=PROJECTTYPE;
    }     

    /**
	* 菜单介绍内容
    */  	
    public  String getContent()
    {
        return  content;             
    }     
    /**
	* 菜单介绍内容
    */  	
    public void setContent (String CONTENT )
    {
        content=CONTENT;
    }     

    /**
	* 其它类型
    */  	
    public  String getExt3childtype()
    {
        return  ext3childtype;             
    }     
    /**
	* 其它类型
    */  	
    public void setExt3childtype (String EXT3CHILDTYPE )
    {
        ext3childtype=EXT3CHILDTYPE;
    }     

    /**
	* 新菜单地址，由于历史数据投标和代理公用了一部分菜单，但地址不能复用，投标系统使用此字段作为地址
    */  	
    public  String getNewpageurl()
    {
        return  newpageurl;             
    }     
    /**
	* 新菜单地址，由于历史数据投标和代理公用了一部分菜单，但地址不能复用，投标系统使用此字段作为地址
    */  	
    public void setNewpageurl (String NEWPAGEURL )
    {
        newpageurl=NEWPAGEURL;
    }     
        


    public <T extends t_sysmenu>  T modelToDto(Class<T> tClass) {
        T dto = null;
        try {
    		dto=tClass.newInstance();
		} catch (Exception e) {
			System.out.println("Impossible");
		}
	    dto.setMenuid(this.getMenuid());
	    dto.setParentid(this.getParentid());
	    dto.setMenutype(this.getMenutype());
	    dto.setImgpath(this.getImgpath());
	    dto.setMenuname(this.getMenuname());
	    dto.setTitle(this.getTitle());
	    dto.setPageurl(this.getPageurl());
	    dto.setSfmx(this.getSfmx());
	    dto.setShbj(this.getShbj());
	    dto.setScbj(this.getScbj());
	    dto.setModifydate(this.getModifydate());
	    dto.setCreatedate(this.getCreatedate());
	    dto.setTargetblank(this.getTargetblank());
	    dto.setLinkmenuid(this.getLinkmenuid());
	    dto.setDetail3(this.getDetail3());
	    dto.setDetail2(this.getDetail2());
	    dto.setDetail(this.getDetail());
	    dto.setMenuversion(this.getMenuversion());
	    dto.setProjecttype(this.getProjecttype());
	    dto.setContent(this.getContent());
	    dto.setExt3childtype(this.getExt3childtype());
	    dto.setNewpageurl(this.getNewpageurl());
        return dto;
    } 

}
 