package com.iairu.reactspringbootdocker.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    Optional<User> findByUsername(String username);

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if username exists
    boolean existsByUsername(String username);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find users by username containing (case insensitive)
    List<User> findByUsernameContainingIgnoreCase(String username);

    // Custom query to find users created after a certain date
    @Query("SELECT u FROM User u WHERE u.createdAt > :date ORDER BY u.createdAt DESC")
    List<User> findUsersCreatedAfter(@Param("date") java.time.LocalDateTime date);

    // Custom query to count total users
    @Query("SELECT COUNT(u) FROM User u")
    long countTotalUsers();
}