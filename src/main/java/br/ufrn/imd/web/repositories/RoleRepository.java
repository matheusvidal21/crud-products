package br.ufrn.imd.web.repositories;

import br.ufrn.imd.web.entities.RoleEntity;
import br.ufrn.imd.web.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(RoleType name);

}
