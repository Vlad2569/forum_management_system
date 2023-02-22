package com.a44.group10.forum_management_system.services;

import com.a44.group10.forum_management_system.exceptions.DuplicateEntityException;
import com.a44.group10.forum_management_system.exceptions.EntityNotFoundException;
import com.a44.group10.forum_management_system.models.User;
import com.a44.group10.forum_management_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getById(int id) {
        return repository.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return repository.getByUsername(username);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return repository.getByFirstName(firstName);
    }

    @Override
    public void create(User user) {
        boolean duplicateUsernameExists = true;
        try {
            repository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            duplicateUsernameExists = false;
        }
        if (duplicateUsernameExists) {
            throw new DuplicateEntityException("User", "username", user.getUsername());
        }
        boolean duplicateEmailExists = true;
        try {
            repository.getByEmail(user.getEmail());
        } catch (EntityNotFoundException e) {
            duplicateEmailExists = false;
        }
        if (duplicateEmailExists) {
            throw new DuplicateEntityException("User", "email", user.getEmail());
        }
        repository.create(user);
    }

    @Override
    public void update(User user) {
        boolean duplicateUsernameExists = true;
        try {
            User existing = repository.getByUsername(user.getUsername());
            if (existing.getId() == user.getId()) {
                duplicateUsernameExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateUsernameExists = false;
        }
        if (duplicateUsernameExists) {
            throw new DuplicateEntityException("User", "username", user.getUsername());
        }
        boolean duplicateEmailExists = true;
        try {
            User existing = repository.getByEmail(user.getEmail());
            if (existing.getId() == user.getId()) {
                duplicateEmailExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateEmailExists = false;
        }
        if (duplicateEmailExists) {
            throw new DuplicateEntityException("User", "email", user.getEmail());
        }
        repository.update(user);
    }
    @Override
    public void delete(int id){
        repository.delete(id);
    }
}
