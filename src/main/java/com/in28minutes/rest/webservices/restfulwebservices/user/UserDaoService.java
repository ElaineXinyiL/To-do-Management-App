package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//data access object
@Component
public class UserDaoService {
    //we first use Static List
    //afterwards, we convert it to JPA/Hibernate

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;
    static {
        users.add(new User(++usersCount,"husky", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"husky1", LocalDate.now().minusYears(10)));
        users.add(new User(++usersCount,"husky2", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
