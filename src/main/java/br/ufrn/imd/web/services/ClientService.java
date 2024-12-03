package br.ufrn.imd.web.services;

import br.ufrn.imd.web.dtos.ClientDTO;
import br.ufrn.imd.web.entities.ClientEntity;
import br.ufrn.imd.web.enums.Gender;
import br.ufrn.imd.web.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> findAll() {
        List<ClientEntity> clients = this.clientRepository.findAll();
        clients.removeIf(client -> !client.getActive());
        return clients;
    }

    public ClientEntity findById(Long id) {
        Optional<ClientEntity> clientOptional = this.clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Client not found");
        }

        if (!clientOptional.get().getActive()) {
            throw new EntityNotFoundException("Client not found");
        }

        return clientOptional.get();
    }

    public ClientEntity save(ClientEntity clientEntity) {
        return this.clientRepository.save(clientEntity);
    }

    public ClientEntity save(ClientDTO clientDTO) {
        ClientEntity clientEntity = ClientEntity.builder()
                .name(clientDTO.name())
                .cpf(clientDTO.cpf())
                .gender(Gender.fromString(clientDTO.gender()))
                .birthDate(clientDTO.birthDate())
                .active(true)
                .build();
        return this.clientRepository.save(clientEntity);
    }

    public void deleteById(Long id) {
        ClientEntity client = this.findById(id);
        this.clientRepository.delete(client);
    }

    public ClientEntity update(Long id, ClientDTO clientDTO) {
        ClientEntity client = this.findById(id);

        client.setName(clientDTO.name() != null ? clientDTO.name() : client.getName());
        client.setCpf(clientDTO.cpf() != null ? clientDTO.cpf() : client.getCpf());
        client.setGender(clientDTO.gender() != null ? Gender.fromString(clientDTO.gender()) : client.getGender());
        client.setBirthDate(clientDTO.birthDate() != null ? clientDTO.birthDate() : client.getBirthDate());

        return this.clientRepository.save(client);
    }

    public void logicDelete(Long id) {
        ClientEntity client = this.findById(id);
        client.setActive(false);
        this.clientRepository.save(client);
    }
}
