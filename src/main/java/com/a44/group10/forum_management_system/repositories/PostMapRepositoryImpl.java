package com.a44.group10.forum_management_system.repositories;

import com.a44.group10.forum_management_system.exceptions.EntityNotFoundException;
import com.a44.group10.forum_management_system.models.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostMapRepositoryImpl implements PostRepository {

    private Map<Integer, Post> posts;

    public PostMapRepositoryImpl() {
        this.posts = new HashMap<>();
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Post getById(int id) {
        Post post = posts.get(id);
        if (post == null){
            throw new EntityNotFoundException("Post", id);
        }
        return post;
    }

    @Override
    public void create(Post post) {
        posts.put(post.getId(), post);
    }

    @Override
    public void update(Post post) {
        Post postToUpdate = getById(post.getId());
        posts.replace(postToUpdate.getId(), post);
    }

    @Override
    public void delete(int id) {
        Post postToDelete = getById(id);
        posts.remove(postToDelete.getId());
    }
}
