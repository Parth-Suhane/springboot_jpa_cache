package com.example.demo.bootstrap;

import com.example.demo.domain.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBootstrap implements CommandLineRunner {
    private final UserRepository ur;
    private final PostRepository pr;
    private final CommentRepository cr;

    public DataBootstrap(UserRepository ur, PostRepository pr, CommentRepository cr) {
        this.ur = ur;
        this.pr = pr;
        this.cr = cr;
    }

    @Override
    public void run(String... args) {
        User u1 = new User("Alice", "alice@example.com"), u2 = new User("Bob", "bob@example.com");
        ur.save(u1);
        ur.save(u2);
        for (int i = 1; i <= 30; i++) {
            Post p = new Post("Title " + i, "Body " + i, (i % 2 == 0) ? u1 : u2);
            pr.save(p);
            cr.save(new Comment("Nice post " + i, p));
            if (i % 3 == 0)
                cr.save(new Comment("Another comment " + i, p));
        }
    }
}