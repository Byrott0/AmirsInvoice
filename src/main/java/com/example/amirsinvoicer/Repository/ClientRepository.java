package com.example.amirsinvoicer.Repository;

import com.example.amirsinvoicer.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Custom query methods can be defined here if needed
    // For example, you can add methods to find clients by name, email, etc.
}
