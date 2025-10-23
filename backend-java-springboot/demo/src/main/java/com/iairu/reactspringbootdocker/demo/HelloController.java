package com.iairu.reactspringbootdocker.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class HelloController {

    @Autowired
    private DatabaseConnectionService databaseConnectionService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/api/hello.json")
    public Map<String, Object> helloJson() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello, World!");
        response.put("timestamp", System.currentTimeMillis());
        response.put("database_healthy", databaseConnectionService.isConnectionHealthy());
        return response;
    }

    @GetMapping("/api/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        boolean dbHealthy = databaseConnectionService.isConnectionHealthy();
        
        response.put("status", dbHealthy ? "UP" : "DOWN");
        response.put("database", dbHealthy ? "CONNECTED" : "DISCONNECTED");
        response.put("timestamp", System.currentTimeMillis());
        
        return response;
    }

    @GetMapping("/api/health.json")
    public Map<String, Object> healthJson() {
        return health();
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/api/users.json")
    public List<User> getAllUsersJson() {
        return userRepository.findAll();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/users/{id}.json")
    public ResponseEntity<User> getUserByIdJson(@PathVariable Long id) {
        return getUserById(id);
    }

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/api/users.json")
    public User createUserJson(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/api/users/count")
    public Map<String, Object> getUserCount() {
        Map<String, Object> response = new HashMap<>();
        response.put("count", userRepository.count());
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @GetMapping("/api/users/count.json")
    public Map<String, Object> getUserCountJson() {
        return getUserCount();
    }
}
