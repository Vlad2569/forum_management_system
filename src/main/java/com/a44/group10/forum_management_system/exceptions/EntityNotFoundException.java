package com.a44.group10.forum_management_system.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String type, int id) {
        this(type, "id", String.valueOf(id));
    }

    public EntityNotFoundException(String type, String attribute, String value) {
        super(String.format("%s with %s %s not found.", type, attribute, value));
    }
}
