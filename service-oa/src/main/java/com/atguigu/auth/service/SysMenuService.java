package com.atguigu.auth.service;

import com.atguigu.model.system.SysMenu;
import com.atguigu.vo.system.AssginMenuVo;
import com.atguigu.vo.system.RouterVo;
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

    /**
     * 根据角色获取授权权限数据
     * @return
     */
    List<SysMenu> findSysMenuByRoleId(Long roleId);

    /**
     * 保存角色权限
     * @param  assginMenuVo
     */
    void doAssign(AssginMenuVo assginMenuVo);

    /**
     * 根据用户id获取用户可以操作的菜单
     */
    List<RouterVo> findUserMenuListByUserId(Long userId);

    /**
     * 根据用户id获取用户可以操作的菜单按钮权限
     */
    List<String> findUserPermsByUserId(Long userId);
}
