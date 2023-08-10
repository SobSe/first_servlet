package ru.sobse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sobse.controller.PostController;
import ru.sobse.repository.PostRepository;
import ru.sobse.service.PostService;

@Configuration
public class JavaConfig {
    @Bean
    // название метода - название бина
    public PostController postController() {
        // вызов метода и есть DI
        return new PostController(postService());
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }
}
