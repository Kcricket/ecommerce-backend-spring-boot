package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.User;
import com.youtube.ecommerce.entity.UserAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressDao extends CrudRepository<UserAddress, Integer> {
    //Find by User
    public List<UserAddress> findByUser(User user);
}
