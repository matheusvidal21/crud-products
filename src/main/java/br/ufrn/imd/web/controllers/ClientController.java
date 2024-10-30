package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.dtos.ClientDTO;
import br.ufrn.imd.web.entities.ClientEntity;
import br.ufrn.imd.web.enums.Gender;
import br.ufrn.imd.web.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAll(){
        List<ClientEntity> clients = this.clientRepository.findAll();
        clients.removeIf(client -> !client.getActive());
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getById(@PathVariable Long id){
        Optional<ClientEntity> clientEntity = this.clientRepository.findById(id);

        if (clientEntity.isEmpty() || !clientEntity.get().getActive()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clientEntity.get());
    }

    @PostMapping
    public ResponseEntity<ClientEntity> create(@RequestBody ClientDTO clientDTO){
        ClientEntity clientEntity = ClientEntity.builder()
                .name(clientDTO.name())
                .cpf(clientDTO.cpf())
                .gender(Gender.fromString(clientDTO.gender()))
                .birthDate(clientDTO.birthDate())
                .build();

        ClientEntity savedClient = this.clientRepository.save(clientEntity);
        return ResponseEntity.created(null).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientEntity> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        Optional<ClientEntity> clientOptional = this.clientRepository.findById(id);

        if (clientOptional.isEmpty() || !clientOptional.get().getActive()) {
            return ResponseEntity.notFound().build();
        }

        ClientEntity clientEntity = clientOptional.get();
        clientEntity.setName(clientDTO.name() != null ? clientDTO.name() : clientEntity.getName());
        clientEntity.setCpf(clientDTO.cpf() != null ? clientDTO.cpf() : clientEntity.getCpf());
        clientEntity.setGender(clientDTO.gender() != null ? Gender.fromString(clientDTO.gender()) : clientEntity.getGender());
        clientEntity.setBirthDate(clientDTO.birthDate() != null ? clientDTO.birthDate() : clientEntity.getBirthDate());

        ClientEntity updatedClient = this.clientRepository.save(clientEntity);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<ClientEntity> clientOptional = this.clientRepository.findById(id);

        if (clientOptional.isEmpty() || !clientOptional.get().getActive()) {
            return ResponseEntity.notFound().build();
        }

        this.clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logic-delete/{id}")
    public ResponseEntity<Void> logicDelete(@PathVariable Long id) {
        Optional<ClientEntity> clientOptional = this.clientRepository.findById(id);

        if (clientOptional.isEmpty() || !clientOptional.get().getActive()) {
            return ResponseEntity.notFound().build();
        }

        ClientEntity clientEntity = clientOptional.get();
        clientEntity.setActive(false);

        this.clientRepository.save(clientEntity);
        return ResponseEntity.noContent().build();
    }

}
