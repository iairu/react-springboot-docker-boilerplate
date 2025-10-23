package com.iairu.reactspringbootdocker.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DatabaseConnectionService implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        testDatabaseConnection();
        testJpaFunctionality();
    }

    public void testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            
            System.out.println("==========================================");
            System.out.println("🗄️  DATABASE CONNECTION TEST");
            System.out.println("==========================================");
            System.out.println("✅ Database connection successful!");
            System.out.println("📊 Database Product Name: " + metaData.getDatabaseProductName());
            System.out.println("📋 Database Product Version: " + metaData.getDatabaseProductVersion());
            System.out.println("🔗 Connection URL: " + metaData.getURL());
            System.out.println("👤 Username: " + metaData.getUserName());
            System.out.println("🏠 Database Schema: " + connection.getSchema());
            System.out.println("⚙️  Driver Name: " + metaData.getDriverName());
            System.out.println("🔢 Driver Version: " + metaData.getDriverVersion());
            System.out.println("==========================================");
            
        } catch (SQLException e) {
            System.err.println("==========================================");
            System.err.println("❌ DATABASE CONNECTION FAILED");
            System.err.println("==========================================");
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
            System.err.println("==========================================");
            e.printStackTrace();
        }
    }
    
    public boolean isConnectionHealthy() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(5); // 5 second timeout
        } catch (SQLException e) {
            System.err.println("⚠️  Database health check failed: " + e.getMessage());
            return false;
        }
    }

    public void testJpaFunctionality() {
        try {
            System.out.println("==========================================");
            System.out.println("🔧 JPA FUNCTIONALITY TEST");
            System.out.println("==========================================");
            
            // Count existing users
            long userCount = userRepository.count();
            System.out.println("👥 Current user count: " + userCount);
            
            // Create a test user if none exist
            if (userCount == 0) {
                User testUser = new User("testuser", "test@example.com");
                User savedUser = userRepository.save(testUser);
                System.out.println("✅ Created test user: " + savedUser);
                
                // Verify the user was saved
                User foundUser = userRepository.findByUsername("testuser").orElse(null);
                if (foundUser != null) {
                    System.out.println("✅ User retrieval successful: " + foundUser.getUsername());
                } else {
                    System.out.println("❌ Failed to retrieve saved user");
                }
            } else {
                // Show existing users
                List<User> allUsers = userRepository.findAll();
                System.out.println("📋 Existing users:");
                for (User user : allUsers) {
                    System.out.println("   - " + user.getUsername() + " (" + user.getEmail() + ")");
                }
            }
            
            // Test custom query
            List<User> recentUsers = userRepository.findUsersCreatedAfter(LocalDateTime.now().minusDays(1));
            System.out.println("📅 Users created in last 24 hours: " + recentUsers.size());
            
            System.out.println("✅ JPA functionality test completed successfully!");
            System.out.println("==========================================");
            
        } catch (Exception e) {
            System.err.println("==========================================");
            System.err.println("❌ JPA FUNCTIONALITY TEST FAILED");
            System.err.println("==========================================");
            System.err.println("Error: " + e.getMessage());
            System.err.println("==========================================");
            e.printStackTrace();
        }
    }
}