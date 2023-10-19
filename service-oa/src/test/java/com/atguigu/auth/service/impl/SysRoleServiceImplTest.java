package com.atguigu.auth.service.impl;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysRoleServiceImplTest {

    @Autowired
    private SysRoleService service;

    @Test
    public void getAll() {
        List<SysRole> list = service.list();
        System.out.println(list);
    }
}