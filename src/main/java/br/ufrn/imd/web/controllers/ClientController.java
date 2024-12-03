package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.dtos.ClientDTO;
import br.ufrn.imd.web.entities.ClientEntity;
import br.ufrn.imd.web.entities.ProductEntity;
import br.ufrn.imd.web.enums.Gender;
import br.ufrn.imd.web.repositories.ClientRepository;
import br.ufrn.imd.web.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAll(){
        return ResponseEntity.ok(this.clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientEntity> create(@RequestBody ClientDTO clientDTO){
        return ResponseEntity.created(null).body(this.clientService.save(clientDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientEntity> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(this.clientService.update(id, clientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logic-delete/{id}")
    public ResponseEntity<Void> logicDelete(@PathVariable Long id) {
        this.clientService.logicDelete(id);
        return ResponseEntity.noContent().build();
    }

}
