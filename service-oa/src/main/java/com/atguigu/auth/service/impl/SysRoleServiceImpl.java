package com.atguigu.auth.service.impl;

import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.auth.service.SysRoleService;
import com.atguigu.auth.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author qlk
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2023-10-19 17:49:41
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

}




