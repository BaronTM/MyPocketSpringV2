package com.myPocket.myPocket.controller.repository;

import com.myPocket.myPocket.model.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
    public Integer countByUsername(String username);

}
