package br.ufrn.imd.web.repositories;

import br.ufrn.imd.web.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
