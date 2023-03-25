package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.entity.ChangePasswordRequest;
import com.youtube.ecommerce.entity.User;
import com.youtube.ecommerce.entity.UserAddress;
import com.youtube.ecommerce.service.UserAddressService;
import com.youtube.ecommerce.service.UserService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAddressService userAddressService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping({"/changeUserPassword"})
    public User changeUserPassword(
            @RequestBody ChangePasswordRequest changePasswordRequest
            ) {
        return userService.changeUserPassword(changePasswordRequest);
    }
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @GetMapping({"/loadUserData"})
    public User loadUserData(
    ){
        return userService.findByUsername();
    }

    @PostMapping({"/addUserAddress"})
    public UserAddress addUserAddress(
            @RequestBody UserAddress userAddress
    ){
        return userAddressService.addNewUserAddress(userAddress);
    }


    @GetMapping({"/getAllUserAddresses"})
    public List<UserAddress> getAllUserAddresses(){
        return userAddressService.getAllUserAdresses();
    }

    @DeleteMapping({"/deleteUserAddress/{id}"})
    public void deleteUserAddress(@PathVariable(name = "id") int id){
        userAddressService.deleteUserAddress(id);
    }

}
