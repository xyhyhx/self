package com.qf.service.impl;

import com.qf.bean.Role2;
import com.qf.dao.RoleDao;
import com.qf.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {
   @Resource
    private RoleDao roleDao;


    @Override
    public int insert(Role2 role) {
        return roleDao.insert(role);
    }
}
