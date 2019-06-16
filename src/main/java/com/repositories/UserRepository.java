package com.repositories;

import com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String name);
    User findById(Integer id);
    User deleteByUsername(String string);

}
