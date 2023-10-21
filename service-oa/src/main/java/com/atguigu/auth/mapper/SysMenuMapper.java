package com.atguigu.auth.mapper;

import com.atguigu.model.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author qlk
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2023-10-19 21:13:59
* @Entity com.atguigu.auth.domain.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findListByUserId(@Param("userId") Long userId);
}




