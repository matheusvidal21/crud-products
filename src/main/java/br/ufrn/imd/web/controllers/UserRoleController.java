package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.dtos.UserRoleDTO;
import br.ufrn.imd.web.entities.RoleEntity;
import br.ufrn.imd.web.entities.UserEntity;
import br.ufrn.imd.web.enums.RoleType;
import br.ufrn.imd.web.services.RoleService;
import br.ufrn.imd.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserRoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add-role-to-user")
    public ResponseEntity<UserEntity> addRoleToUser(@RequestBody @Valid UserRoleDTO userRoleDTO) {
        UserEntity user = this.userService.findById(userRoleDTO.userId());
        RoleEntity role = this.roleService.findByRoleName(RoleType.fromString(userRoleDTO.role()));
        user.addRole(role);
        return ResponseEntity.ok(this.userService.save(user));
    }

}
