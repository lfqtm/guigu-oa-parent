package com.atguigu.auth.service;

import com.atguigu.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author qlk
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
* @createDate 2023-10-19 21:13:59
*/
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单树形数据
     * @return
     */
    List<SysMenu> findNodes();

}
