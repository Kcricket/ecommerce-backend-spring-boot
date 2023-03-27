package com.youtube.ecommerce.service;

import com.youtube.ecommerce.configuration.JwtRequestFilter;
import com.youtube.ecommerce.dao.UserAddressDao;
import com.youtube.ecommerce.dao.UserDao;
import com.youtube.ecommerce.entity.User;
import com.youtube.ecommerce.entity.UserAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAddressServiceTest {
    @Mock
    private UserAddressDao userAddressDao;
    @Mock
    private UserDao userDao;
    private UserAddressService userAddressService;

    @BeforeEach
    void setUp() {
        userAddressService = new UserAddressService(userAddressDao, userDao);
    }

    public User initTestUser(){
        //Create example user and user address
        User user = new User("testuser", "John", "Doe", "johndoe@example.com", "password", null, "1990-01-01");
        return user;
    }


    @Test
    void addNewUserAddress() {
        //Given
        User user = initTestUser();
        UserAddress userAddress = new UserAddress("USA", "New York", "10001", "5th Ave", "1", "Apt 10", null);

        when(userDao.findById(user.getUserName())).thenReturn(Optional.of(user));
        JwtRequestFilter.USERNAME = user.getUserName();

        when(userAddressDao.save(userAddress)).thenReturn(userAddress);
        //When
        UserAddress result = userAddressService.addNewUserAddress(userAddress);
        //Then
        assertEquals(userAddress, result);
        //See if user has been set
        assertEquals(user, result.getUser());
        verify(userAddressDao).save(userAddress);
    }

    @Test
    void getAllUserAdresses() {
        //Given
        User user = initTestUser();
        UserAddress address1 = new UserAddress("USA", "New York", "10001", "Broadway", "123", "Apt 1", user);
        UserAddress address2 = new UserAddress("USA", "Boston", "02108", "Beacon St", "456", "Apt 2", user);
        List<UserAddress> addresses = Arrays.asList(address1, address2);

        when(userDao.findById(user.getUserName())).thenReturn(Optional.of(user));
        JwtRequestFilter.USERNAME = user.getUserName();

        when(userAddressDao.findByUser(user)).thenReturn(addresses);

        //When
        List<UserAddress> result = userAddressService.getAllUserAdresses();

        //Then
        assertEquals(addresses, result);
        //See if user has been set
        assertEquals(addresses.get(0).getUser(), user);
        verify(userAddressDao).findByUser(user);

    }

    @Test
    void deleteUserAddress() {
        //Given
        int addressId = 1;

        // When
        userAddressService.deleteUserAddress(addressId);

        // Assert
        verify(userAddressDao, times(1)).deleteById(addressId);


    }
}