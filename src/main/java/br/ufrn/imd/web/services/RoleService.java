package br.ufrn.imd.web.services;

import br.ufrn.imd.web.entities.RoleEntity;
import br.ufrn.imd.web.enums.RoleType;
import br.ufrn.imd.web.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity findByRoleName(RoleType name) {
        return this.roleRepository.findByRoleName(name).orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

}
