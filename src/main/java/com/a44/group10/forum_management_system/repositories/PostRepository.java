package com.a44.group10.forum_management_system.repositories;

import com.a44.group10.forum_management_system.models.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getAll();

    Post getById(int id);

    void create(Post post);

    void update(Post post);

    void delete(int id);
}
