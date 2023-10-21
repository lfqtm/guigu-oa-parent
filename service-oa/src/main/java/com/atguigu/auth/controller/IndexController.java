package com.atguigu.auth.controller;

import com.atguigu.auth.service.SysMenuService;
import com.atguigu.auth.service.SysUserService;
import com.atguigu.common.config.exception.GuiguException;
import com.atguigu.common.jwt.JwtHelper;
import com.atguigu.common.result.Result;
import com.atguigu.common.utils.MD5;
import com.atguigu.model.system.SysUser;
import com.atguigu.vo.system.LoginVo;
import com.atguigu.vo.system.RouterVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录接口
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysMenuService sysMenuService;


    /**
     * 登录
     *
     * @return
     */
    @ApiOperation(value = "登录接口")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        /*Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.ok(map);*/

        String username = loginVo.getUsername();
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserService.getOne(wrapper);

        if (sysUser == null) throw new GuiguException(201, "用户不存在");

        // md5密码比较
        String passwordMD5 = sysUser.getPassword();
        String encrypt = MD5.encrypt(loginVo.getPassword());
        if (!encrypt.equals(passwordMD5)) throw new GuiguException(201, "密码错误");

        if (sysUser.getStatus() == 0) throw new GuiguException(201, "用户已被禁用");

        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public Result<Map<String, Object>> info(HttpServletRequest request) {

//        String token = request.getHeader("header");
//        Long userId = JwtHelper.getUserId(token);
//        SysUser sysUser = sysUserService.getById(userId);
//        List<RouterVo> routerList = sysMenuService.findUserMenuListByUserId(userId);
//        List<String> permsList =  sysMenuService.findUserPermsByUserId(userId);
//
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("roles", sysUser.getRoleList());
//        map.put("name", sysUser.getUsername());
//        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
//        map.put("routers", routerList);
//        map.put("buttons", permsList);
//
//        return Result.ok(map);

        String username = JwtHelper.getUsername(request.getHeader("token"));
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("logout")
    public Result logout() {
        return Result.ok();
    }

}
