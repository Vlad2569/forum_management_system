package com.a44.group10.forum_management_system.services;

import com.a44.group10.forum_management_system.models.User;

import java.util.List;

public interface UserService  {

    List<User> getAll();

    User getById(int id);

    User getByUsername(String username);

    User getByEmail(String email);

    List<User> getByFirstName(String firstName);

    void create(User user);

    void update(User user);
    void delete(int id);
}
