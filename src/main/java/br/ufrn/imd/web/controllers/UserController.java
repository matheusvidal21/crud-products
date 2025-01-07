package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.configs.security.AuthenticationCurrentUserService;
import br.ufrn.imd.web.dtos.UpdatePasswordDTO;
import br.ufrn.imd.web.entities.UserEntity;
import br.ufrn.imd.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationCurrentUserService authenticationCurrentUserService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("update-password")
    public ResponseEntity<UserEntity> updatePassword(@RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        UserEntity currentUser = this.authenticationCurrentUserService.getCurrentUser();
        if (!currentUser.getId().equals(updatePasswordDTO.userId())) {
            throw new IllegalArgumentException("You can only update your own password");
        }

        return ResponseEntity.ok(this.userService.updatePassword(updatePasswordDTO.userId(), updatePasswordDTO.password()));
    }
}
