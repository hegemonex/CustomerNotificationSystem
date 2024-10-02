package ge.croco.customernotificationsystem;

import ge.croco.customernotificationsystem.model.UserDTO;
import ge.croco.customernotificationsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerNotificationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerNotificationSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserService userService) {
        return args -> {
            // Check if default user exists
            if (!userService.userExists("admin")) {
                // Create a default admin user
                UserDTO adminUser = new UserDTO();
                adminUser.setUsername("admin");
                adminUser.setPassword("123");

                userService.saveUser(adminUser);
                System.out.println("Default admin user created: admin / admin123");
            }
        };
    }

}
