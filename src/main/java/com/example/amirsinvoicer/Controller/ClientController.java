package com.example.amirsinvoicer.Controller;

import com.example.amirsinvoicer.Model.Client;
import com.example.amirsinvoicer.Service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
       return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(UUID id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public void addClient(@RequestBody Client client) {
        clientService.insertClient(client);
    }

    @DeleteMapping
    public void deleteClient(@RequestBody Client client) {
        clientService.deleteClient(client);
    }

    @PutMapping
    public void updateClient(@PathVariable UUID id,
                              @RequestBody Client client) {
        clientService.updateClient(id, client);
    }
}
