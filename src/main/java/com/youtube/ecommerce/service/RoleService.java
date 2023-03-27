package com.youtube.ecommerce.service;

import com.youtube.ecommerce.dao.RoleDao;
import com.youtube.ecommerce.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RoleService() {
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
