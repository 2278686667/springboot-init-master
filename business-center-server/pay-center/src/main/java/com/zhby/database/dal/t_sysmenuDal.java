package com.zhby.database.dal;

import java.util.List;


import com.zhby.base.BaseDal;
import com.zhby.database.dto.treeTSysMenu;
import com.zhby.database.model.t_sysmenu;
import org.springframework.stereotype.Service;

@Service
public   class t_sysmenuDal extends BaseDal<t_sysmenu>
{

    public List<treeTSysMenu> getMenuOfLogin(String userid) {
        String sql ="select c.menuid as id,c.parentid as pid,c.menuname as name,c.pageurl as url,c.imgpath as menuIco,c.imgpath as menuico"
                + " from t_sysmenu c"
                + " where  menutype='p'"
                + " order by menuid";
        return jdbcDao.queryListForSql(sql, treeTSysMenu.class);
    }
}
