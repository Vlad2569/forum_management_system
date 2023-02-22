package com.a44.group10.forum_management_system.repositories;

import com.a44.group10.forum_management_system.exceptions.EntityNotFoundException;
import com.a44.group10.forum_management_system.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users;

    public UserRepositoryImpl() {
        users = new ArrayList<>();
        User user = new User(1, "maria@abv.bg", "username1", "Maria",
                "Monova", "mariamonova123");
        user.setAdmin(true);
        users.add(user);
        users.add(new User(2, "atanas@gmail.com", "username2", "Atanas",
                "Maev", "atanasmaev456"));
        users.add(new User(3, "nona@yahoo.com", "username3", "Nona",
                "Spasova", "nonaspasova789"));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User getById(int id) {
        return getAll().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }

    @Override
    public User getByUsername(String username) {
        return getAll().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User", "username", username));
    }

    @Override
    public User getByEmail(String email) {
        return getAll().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User", "email", email));
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        List<User> sameNameUsers = getAll().stream()
                .filter(user -> user.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                .toList();
        if (sameNameUsers.isEmpty()) {
            throw new EntityNotFoundException("User", "first name", firstName);
        }
        return sameNameUsers;
    }

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public void update(User user) {
        User userToUpdate = getById(user.getId());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setPassword(user.getPassword());
    }

    @Override
    public void delete(int id) {
        User userToDelete = getById(id);
        users.remove(userToDelete);
    }


}
