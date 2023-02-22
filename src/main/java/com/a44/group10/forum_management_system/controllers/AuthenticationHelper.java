package com.a44.group10.forum_management_system.controllers;

import com.a44.group10.forum_management_system.exceptions.EntityNotFoundException;
import com.a44.group10.forum_management_system.models.User;
import com.a44.group10.forum_management_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthenticationHelper {
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final UserService service;

    @Autowired
    public AuthenticationHelper(UserService service) {
        this.service = service;
    }
    public User tryGetUser(HttpHeaders headers){
        if (!headers.containsKey(AUTHORIZATION_HEADER_NAME)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "The requested resource requires authentication.");
        }
        try{
            String username = headers.getFirst(AUTHORIZATION_HEADER_NAME);
            return service.getByUsername(username);
        } catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username.");
        }
    }
}
