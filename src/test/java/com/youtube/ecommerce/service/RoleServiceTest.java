package com.youtube.ecommerce.service;

import com.youtube.ecommerce.dao.RoleDao;
import com.youtube.ecommerce.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    @Mock
    private RoleDao roleDao;
    private RoleService roleService;

    @BeforeEach
    void setUp() {

        roleService = new RoleService(roleDao);
    }

    @Test
    void createNewRole() {
        //Given a role
        Role role = new Role();
        role.setRoleName("User");
        role.setRoleDescription("Default user role");

        when(roleDao.save(role)).thenReturn(role);
        //When
        Role result = roleService.createNewRole(role);
        //Then
        verify(roleDao).save(role);
        assertEquals(role, result);

    }
}