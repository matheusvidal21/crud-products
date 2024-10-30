package br.ufrn.imd.web.repositories;

import br.ufrn.imd.web.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
