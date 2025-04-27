package com.example.amirsinvoicer.Service;

import com.example.amirsinvoicer.Model.Client;
import com.example.amirsinvoicer.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // methodes
    public void insertClient(Client client) {
        client.setId(null);
        clientRepository.save(client);
    }
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Client getClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public void updateClient(UUID id, Client update) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));

        client.setName(update.getName());
        client.setEmail(update.getEmail());
        client.setAddress(update.getAddress());
        client.setPhoneNumber(update.getPhoneNumber());
        clientRepository.save(client);
    }

}
