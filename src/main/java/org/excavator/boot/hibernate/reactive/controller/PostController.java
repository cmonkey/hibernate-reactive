package org.excavator.boot.hibernate.reactive.controller;

import org.excavator.boot.hibernate.reactive.entity.Post;
import org.excavator.boot.hibernate.reactive.repository.PostRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/posts/v1")
public class PostController {
    @Resource
    PostRepository postRepository;
    @RequestMapping("/")
    public String index() {
        return "Greetings from spring boot and hibernate reactive! " + postRepository.findAll().toString();
    }

    @RequestMapping("/save")
    public Mono<Post> save(@RequestBody Post post){
        postRepository.save(post);
        return Mono.just(postRepository.findById(post.getId()));
    }
}
