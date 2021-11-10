package org.excavator.boot.hibernate.reactive.repository;

import lombok.RequiredArgsConstructor;
import org.excavator.boot.hibernate.reactive.entity.Post;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostRepository {

    private final SessionFactory sessionFactory;

    public java.util.List<Post> findAll(){
        var query = sessionFactory.getCriteriaBuilder().createQuery(Post.class);
        var root = query.from(Post.class);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }
    public Post findById(UUID id){
        return sessionFactory.getCurrentSession().find(Post.class, id);
    }
    public void save(Post post){
        sessionFactory.getCurrentSession().save(post);
    }
    public void delete(Post post){
        sessionFactory.getCurrentSession().delete(post);
    }
}
