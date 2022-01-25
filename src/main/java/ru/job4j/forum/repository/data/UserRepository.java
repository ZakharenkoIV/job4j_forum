package ru.job4j.forum.repository.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("FROM User u WHERE u.username =?1")
    User findUserByName(String name);
}
