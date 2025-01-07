package br.ufrn.imd.web.services;

import br.ufrn.imd.web.entities.UserEntity;
import br.ufrn.imd.web.repositories.RoleRepository;
import br.ufrn.imd.web.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    public Page<UserEntity> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public void delete(UserEntity userEntity){
        this.userRepository.delete(userEntity);
    }

    @Transactional
    public UserEntity register(UserEntity userEntity) {
        if (this.existsByUsername(userEntity.getUsername())){
            throw new EntityExistsException("Username already exists");
        }
        if (this.existsByEmail(userEntity.getEmail())){
            throw new EntityExistsException("Email already exists");
        }

        return this.save(userEntity);
    }

    @Transactional
    public UserEntity save(UserEntity userEntity) {
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        return this.userRepository.save(userEntity);
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public UserEntity updatePassword(Long userId, String password) {
        UserEntity userEntity = this.findById(userId);
        userEntity.setPassword(this.passwordEncoder.encode(password));
        return this.userRepository.save(userEntity);
    }

}
