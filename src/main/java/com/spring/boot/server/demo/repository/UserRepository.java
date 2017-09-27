package com.spring.boot.server.demo.repository;

import com.spring.boot.server.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{
}
