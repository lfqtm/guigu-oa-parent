package com.atguigu.auth.service;

import com.atguigu.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author qlk
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-10-19 18:14:02
*/
public interface SysUserService extends IService<SysUser> {

    void updateStatus(Long id, Integer status);

    /**
     * 根据用户名获取用户登录信息
     */
    Map<String, Object> getUserInfo(String username);

    SysUser getByUsername(String username);
}
