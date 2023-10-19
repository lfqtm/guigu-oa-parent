package com.atguigu.auth.service.impl;

import com.atguigu.auth.service.SysUserRoleService;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.auth.service.SysRoleService;
import com.atguigu.auth.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author qlk
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2023-10-19 17:49:41
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public Map<String, Object> findRoleByAdminId(Long userId) {
        List<SysRole> allRoleList = baseMapper.selectList(null);

        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> existUserRoleList = sysUserRoleService.list(wrapper);

        List<Long> existUserRoleIdList = existUserRoleList.stream()
                .map(SysUserRole::getRoleId).collect(Collectors.toList());

        // find SysRole in allRoleList by existUserRoleIdList
        List<SysRole> assignRoleList = new ArrayList<>();
        for (SysRole sysRole : allRoleList) {
            if (existUserRoleIdList.contains(sysRole.getId())) {
                assignRoleList.add(sysRole);
            }
        }

        // use map to return assignRoleList & allRoleList
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assginRoleList", assignRoleList);
        roleMap.put("allRolesList", allRoleList);
        return roleMap;
    }

    @Transactional
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        // delete exist role then assign new
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, assginRoleVo.getUserId());
        sysUserRoleService.remove(wrapper);

        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (StringUtils.isEmpty(roleId)) continue;
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleService.save(sysUserRole);
        }
    }
}




