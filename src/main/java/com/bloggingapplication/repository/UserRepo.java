package com.bloggingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloggingapplication.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User getByName(String userName);

	public Optional<User> findByEmail(String email);
}
