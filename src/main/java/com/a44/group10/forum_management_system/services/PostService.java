package com.a44.group10.forum_management_system.services;

import com.a44.group10.forum_management_system.models.Post;
import com.a44.group10.forum_management_system.models.User;

import java.util.List;

public interface PostService {
    List<Post> getAll();

    Post getById(int id);

    void create(Post post);

    void update(Post post);

    void delete(int id);
}
