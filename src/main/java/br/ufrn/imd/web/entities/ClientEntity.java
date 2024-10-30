package br.ufrn.imd.web.entities;

import br.ufrn.imd.web.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_clients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    private Boolean active = true;

    @PrePersist
    public void prePersist(){
        if (active == null) {
            active = true;
        }
    }

}
