package com.youtube.ecommerce.service;

import com.youtube.ecommerce.configuration.JwtRequestFilter;
import com.youtube.ecommerce.dao.UserAddressDao;
import com.youtube.ecommerce.dao.UserDao;
import com.youtube.ecommerce.entity.User;
import com.youtube.ecommerce.entity.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressDao userAddressDao;
    @Autowired
    private UserDao userDao;


    public UserAddress addNewUserAddress(UserAddress userAddress) {
        String username = JwtRequestFilter.USERNAME;
        User user = userDao.findById(username).get();
        userAddress.setUser(user);
        return userAddressDao.save(userAddress);

    }


    public List<UserAddress> getAllUserAdresses(){
        String username = JwtRequestFilter.USERNAME;
        User user = userDao.findById(username).get();
        return userAddressDao.findByUser(user);
    }

    public void deleteUserAddress(int id) {
        userAddressDao.deleteById(id);
    }


    public UserAddressService() {
    }

    public UserAddressService(UserAddressDao userAddressDao, UserDao userDao) {
        this.userAddressDao = userAddressDao;
        this.userDao = userDao;
    }

    public UserAddressDao getUserAddressDao() {
        return userAddressDao;
    }

    public void setUserAddressDao(UserAddressDao userAddressDao) {
        this.userAddressDao = userAddressDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
