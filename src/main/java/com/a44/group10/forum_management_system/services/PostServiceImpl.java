package com.a44.group10.forum_management_system.services;

import com.a44.group10.forum_management_system.models.Post;
import com.a44.group10.forum_management_system.models.User;
import com.a44.group10.forum_management_system.repositories.PostRepository;
import com.a44.group10.forum_management_system.repositories.PostRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {

    private PostRepository repository;
    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Post> getAll(){
        return repository.getAll();
    }

    @Override
    public Post getById(int id){
        return repository.getById(id);
    }

    @Override
    public void create(Post post){
        repository.create(post);
    }

    @Override
    public void update(Post post){
        repository.update(post);
    }

    @Override
    public void delete(int id){
        repository.delete(id);
    }
}
