package br.ufrn.imd.web.entities;

import br.ufrn.imd.web.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String brand;

    private LocalDate manufacturingDate;

    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Boolean active = true;

    @PrePersist
    public void prePersist(){
        if (active == null) {
            active = true;
        }
    }
}
