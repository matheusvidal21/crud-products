package br.ufrn.imd.web.configs;

import br.ufrn.imd.web.entities.UserEntity;
import br.ufrn.imd.web.enums.RoleType;
import br.ufrn.imd.web.repositories.UserRepository;
import br.ufrn.imd.web.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("admin")) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin"));

            var adminRole = roleRepository.findByRoleName(RoleType.fromString("ROLE_ADMIN"))
                    .orElseThrow(() -> new RuntimeException("Role ROLE_ADMIN not found"));

            admin.addRole(adminRole);
            userRepository.save(admin);
            System.out.println("Admin user created with encrypted password.");
        }
    }
}
