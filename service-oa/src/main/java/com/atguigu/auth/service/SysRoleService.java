package com.atguigu.auth.service;

import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author qlk
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2023-10-19 17:49:41
*/
public interface SysRoleService extends IService<SysRole> {

    /**
     * 查 所有角色 和 当前用户所属角色
     */
    Map<String, Object> findRoleByAdminId(Long userId);

    /**
     * 为用户分配角色
     */
    void doAssign(AssginRoleVo assginRoleVo);
}
