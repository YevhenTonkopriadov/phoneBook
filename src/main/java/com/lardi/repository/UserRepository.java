package com.lardi.repository;

import com.lardi.model.User;


public interface UserRepository {

    User findByUsername(String username);
    User save(User user);

}
