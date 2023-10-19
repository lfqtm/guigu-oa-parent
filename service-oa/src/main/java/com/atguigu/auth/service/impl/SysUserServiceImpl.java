package com.atguigu.auth.service.impl;

import com.atguigu.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.auth.service.SysUserService;
import com.atguigu.auth.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author qlk
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-10-19 18:14:02
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Transactional
    @Override
    public void updateStatus(Long id, Integer status) {
        SysUser sysUser = this.getById(id);
        if(status.intValue() == 1) {
            sysUser.setStatus(status);
        } else {
            sysUser.setStatus(0);
        }
        this.updateById(sysUser);
    }
}




