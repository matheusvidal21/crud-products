package br.ufrn.imd.web.repositories;

import br.ufrn.imd.web.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
