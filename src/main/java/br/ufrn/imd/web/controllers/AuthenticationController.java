package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.configs.security.JwtProvider;
import br.ufrn.imd.web.dtos.JwtDTO;
import br.ufrn.imd.web.dtos.LoginDTO;
import br.ufrn.imd.web.dtos.UserDTO;
import br.ufrn.imd.web.entities.RoleEntity;
import br.ufrn.imd.web.entities.UserEntity;
import br.ufrn.imd.web.enums.RoleType;
import br.ufrn.imd.web.services.RoleService;
import br.ufrn.imd.web.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid UserDTO userDto) {
        logger.debug("POST registerUser userDto received {}", userDto.toString());
        RoleEntity role = this.roleService.findByRoleName(RoleType.ROLE_USER);
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDto, user);
        user.addRole(role);
        this.userService.register(user);
        logger.info("User saved successfully userId {}", user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> loginUser(@RequestBody @Valid LoginDTO loginDto) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtDTO(jwt));
    }
}
